//package com.lct.rpc;
//
//import com.alipay.remoting.exception.CodecException;
//import com.alipay.remoting.serialization.Serializer;
//
//public class StringSerializer implements Serializer {
//    @Override
//    public byte[] serialize(Object o) throws CodecException {
////        String str = (String) o;
//        String ss = JSONObject.toJSONString(o);
//        return ss.getBytes();
//    }
//
//    @Override
//    public <T> T deserialize(byte[] bytes, String s) throws CodecException {
//        try {
//            Class<?>  ss=  Class.forName(s);
//            return (T) JSONObject.parseObject(new String(bytes),
//                    ss );
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
