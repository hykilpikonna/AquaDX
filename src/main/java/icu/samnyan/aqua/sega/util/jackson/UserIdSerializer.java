package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class UserIdSerializer extends StdSerializer<UserData> {

    public UserIdSerializer() {
        this(null);
    }

    public UserIdSerializer(Class<UserData> t) {
        super(t);
    }

    @Override
    public void serialize(UserData value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value.getCard().getExtId());
    }
}
