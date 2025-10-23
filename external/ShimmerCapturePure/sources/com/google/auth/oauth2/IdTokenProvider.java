package com.google.auth.oauth2;

import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public interface IdTokenProvider {
    IdToken idTokenWithAudience(String str, List<Option> list) throws IOException;

    public enum Option {
        FORMAT_FULL("formatFull"),
        LICENSES_TRUE("licensesTrue"),
        INCLUDE_EMAIL("includeEmail");

        private String option;

        Option(String str) {
            this.option = str;
        }

        public String getOption() {
            return this.option;
        }
    }
}
