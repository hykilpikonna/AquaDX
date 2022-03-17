package icu.samnyan.aqua.sega.billing;

import com.fasterxml.jackson.databind.ObjectMapper;

import icu.samnyan.aqua.sega.billing.model.response.BillingResponse;
import icu.samnyan.aqua.sega.billing.util.Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BillingController {

    private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final ResourceLoader resourceLoader;

    public BillingController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping(value = "/request", produces = "text/plain")
    public String powerOn(InputStream dataStream, HttpServletRequest req) throws IOException {

        RSAPrivateKey key = loadBillingKey();

        byte[] bytes = dataStream.readAllBytes();
        Map<String, String> reqMap = Decoder.decode(bytes);

        logger.info("Request: Billing, " + mapper.writeValueAsString(reqMap));

        String keychipId = reqMap.getOrDefault("keychipid", "");

        BillingResponse resp = new BillingResponse(
                0,
                100,
                1,
                "",
                1024,
                signWithKey(key, keychipId, 1024),
                "1.000",
                66048, // 0x00010200
                signWithKey(key, keychipId, 66048),
                0,
                5,
                "000000/0:000000/0:000000/0");
        logger.info("Response: " + mapper.writeValueAsString(resp));
        return resp.toString().concat("\n");
    }

    private String signWithKey(RSAPrivateKey key, String keychipId, int val) {
        String result = "";

        ByteBuffer sigbytes = ByteBuffer.allocate(15);
        sigbytes.order(ByteOrder.LITTLE_ENDIAN);
        sigbytes.putInt(0, val);
        sigbytes.put(4, keychipId.getBytes(), 0, keychipId.getBytes().length);

        Signature sig;
        try {
            sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(key);
            sig.update(sigbytes);
            byte[] signedData = sig.sign();
            result = bytesToHex(signedData);
        } catch (Exception e) {
            logger.error("Failed to sign with billing key, " + e.getMessage());
        }

        return result;
    }

    private RSAPrivateKey loadBillingKey() {
        RSAPrivateKey billingKey = null;
        
        Resource keyRes = resourceLoader.getResource("classpath:billing.der");
        byte[] key;
        try {
            key = FileCopyUtils.copyToByteArray(keyRes.getInputStream());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            KeySpec keySpec = new PKCS8EncodedKeySpec(key);
            billingKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            logger.error("Failed to load billing key file, " + e.getMessage());
        }

        return billingKey;
    }

    private String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
