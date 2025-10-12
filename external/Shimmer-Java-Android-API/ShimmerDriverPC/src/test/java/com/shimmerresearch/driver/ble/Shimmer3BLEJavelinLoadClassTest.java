package com.shimmerresearch.driver.ble;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class Shimmer3BLEJavelinLoadClassTest {
    public static Shimmer3BLEJavelinLoadClassTest s_t;
    static byte[] l_bytes = {0x3F};//hwid
    static byte[] t_bytes = {0x06};
    static String l_device = "BluetoothLE#BluetoothLE8c:b8:7e:0b:48:2e-e8:eb:1b:93:68:dd";
    static String l_name = "";
    static URLClassLoader classLoader;
    static Class<?> loadedClass;

    public Shimmer3BLEJavelinLoadClassTest() {
        Object instance;
        try {
            instance = loadedClass.newInstance();

            System.out.println("Hello");


            Method method = loadedClass.getMethod("listBLEDevices");

            method.invoke(instance);
            System.out.println("Back in Java:");
            {

				/*l_name = javelin.getBLEDeviceName(l_device);
			String l_services[] = javelin.listBLEDeviceServices(l_device);
			String l_chars[] = javelin.listBLEServiceCharacteristics(l_device, "49535343-fe7d-4ae5-8fa9-9fafd205e455".toUpperCase());
			System.out.println("  Name: "+l_name);
			boolean connected = javelin.watchBLECharacteristicChanges(l_device,
					"49535343-fe7d-4ae5-8fa9-9fafd205e455".toUpperCase(),
					"49535343-1e4d-4bd9-ba61-23c647249616".toUpperCase());
			if (connected) {
			}
				 */
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String getAbsoluteDLLPath(String dllPath) {
        String jarDirectory;
        try {
            jarDirectory = new File(Shimmer3BLEJavelinLoadClassTest.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI())
                    .getParent();
            String directory = new File(jarDirectory).getParent();
            String absoluteDLLPath = new File(directory, dllPath).getAbsolutePath();
            return absoluteDLLPath;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";


    }

    public static void main(String[] args) {
        try {


            String dllPath = "libs/javelin.dll"; // Replace with the actual relative path



            System.out.println("Loading dlls");
            System.load(getAbsoluteDLLPath("libs/javelin.dll"));
            System.load(getAbsoluteDLLPath("libs/msvcp140d_app.dll"));
            System.load(getAbsoluteDLLPath("libs/vcruntime140_1d_app.dll"));
            System.load(getAbsoluteDLLPath("libs/VCRUNTIME140D_APP.dll"));
            System.load(getAbsoluteDLLPath("libs/CONCRT140D_APP.dll"));
            System.load(getAbsoluteDLLPath("libs/ucrtbased.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-synch-l1-2-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-synch-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-processthreads-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-debug-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-errorhandling-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-string-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-profile-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-sysinfo-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-interlocked-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-winrt-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-heap-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-memory-l1-1-0.dll"));
            System.load(getAbsoluteDLLPath("libs/api-ms-win-core-libraryloader-l1-2-0.dll"));
            System.load(getAbsoluteDLLPath("libs/OLEAUT32.dll"));



            File jarFile = new File(getAbsoluteDLLPath("libs/javelin.jar"));
            URL jarUrl = jarFile.toURI().toURL();

            classLoader = new URLClassLoader(new URL[]{jarUrl});

            String className = "javelin.javelin";
            loadedClass = classLoader.loadClass(className);

            Object instance = loadedClass.newInstance();

            System.out.println("Loaded class: " + loadedClass.getName());



        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("dlls loaded");
        s_t = new Shimmer3BLEJavelinLoadClassTest();
    }

}
