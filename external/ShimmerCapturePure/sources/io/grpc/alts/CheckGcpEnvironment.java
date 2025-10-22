package io.grpc.alts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SystemUtils;

/* loaded from: classes2.dex */
final class CheckGcpEnvironment {
    private static final String DMI_PRODUCT_NAME = "/sys/class/dmi/id/product_name";
    private static final String WINDOWS_COMMAND = "powershell.exe";
    private static final Logger logger = Logger.getLogger(CheckGcpEnvironment.class.getName());
    private static Boolean cachedResult = null;

    private CheckGcpEnvironment() {
    }

    static synchronized boolean isOnGcp() {
        if (cachedResult == null) {
            cachedResult = Boolean.valueOf(isRunningOnGcp());
        }
        return cachedResult.booleanValue();
    }

    static boolean checkProductNameOnLinux(BufferedReader bufferedReader) throws IOException {
        String strTrim = bufferedReader.readLine().trim();
        return strTrim.equals("Google") || strTrim.equals("Google Compute Engine");
    }

    static boolean checkBiosDataOnWindows(BufferedReader bufferedReader) throws IOException {
        String line;
        do {
            line = bufferedReader.readLine();
            if (line == null) {
                return false;
            }
        } while (!line.startsWith("Manufacturer"));
        return line.substring(line.indexOf(58) + 1).trim().equals("Google");
    }

    private static boolean isRunningOnGcp() {
        try {
            if (SystemUtils.IS_OS_LINUX) {
                return checkProductNameOnLinux(Files.newBufferedReader(Paths.get(DMI_PRODUCT_NAME, new String[0]), StandardCharsets.UTF_8));
            }
            if (SystemUtils.IS_OS_WINDOWS) {
                return checkBiosDataOnWindows(new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[0]).command(WINDOWS_COMMAND, "Get-WmiObject", "-Class", "Win32_BIOS").start().getInputStream(), StandardCharsets.UTF_8)));
            }
            return false;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Fail to read platform information: ", (Throwable) e);
            return false;
        }
    }
}
