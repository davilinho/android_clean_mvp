package com.wtransnet.app.cleancode.app.net;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * <p>Title: RetrofitJacksonConverter </p>
 * <p>Description: Converter de Jackson, que usa Retrofit. </p>
 * <p>Copyright: Copyright (c) March 2014 </p>
 * <p>Company: Wotrant S.L.</p>
 *
 * @author <a href="mailto:dmartin@wtransnet.com">David Martín</a>
 * @version 1.0
 */
public class RetrofitJacksonConverter implements Converter {

    /**
     * Instancia de ObjectMapper, encargada de realizar las conversiones de Jackson.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructor por defecto.
     */
    public RetrofitJacksonConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {

        JavaType javaType = objectMapper.getTypeFactory().constructType(type);

        try {

            return objectMapper.readValue(body.in(), javaType);

        } catch (IOException e) {

            throw new ConversionException(e);

        }

    }

    @Override
    public TypedOutput toBody(Object object) {

        try {

            String charset = "UTF-8";

            return new JsonTypedOutput(objectMapper.writeValueAsString(object).getBytes(charset), charset);

        } catch (IOException e) {

            throw new AssertionError(e);

        }

    }

    /**
     * Clase estática que gestiona las conversiones con bytes.
     *
     * @author dmartin
     *
     */
    private static class JsonTypedOutput implements TypedOutput {

        /** Json en bytes. */
        private final byte[] jsonBytes;

        /** Mime type format. */
        private final String mimeType;

        /**
         * Constructor con parámetros.
         *
         * @param jsonBytes Json en bytes.
         * @param encode Encoding.
         */
        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }

    }

}
