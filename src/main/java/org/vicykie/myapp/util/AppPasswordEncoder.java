package org.vicykie.myapp.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * Created by vicykie on 2016/6/15.
 * 密码加密
 */
public class AppPasswordEncoder extends MessageDigestPasswordEncoder {
    public AppPasswordEncoder(String algorithm) {
        super(algorithm);
    }

    public AppPasswordEncoder(String algorithm, boolean encodeHashAsBase64) throws IllegalArgumentException {
        super(algorithm, encodeHashAsBase64);
    }

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return super.encodePassword(rawPass, salt);
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return super.isPasswordValid(encPass, rawPass, salt);
    }

    @Override
    public String getAlgorithm() {
        return super.getAlgorithm();
    }

    @Override
    public void setIterations(int iterations) {
        super.setIterations(iterations);
    }
}
