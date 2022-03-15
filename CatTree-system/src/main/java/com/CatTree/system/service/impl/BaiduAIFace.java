package com.CatTree.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.CatTree.common.exception.ServiceException;
import org.apache.logging.log4j.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BaiduAIFace {

    private static final Logger logger = LoggerFactory.getLogger(BaiduAIFace.class);
    private static String token = GetToken.getAuth();

    /**
     * 人脸注册
     */
    public String FaceRegistration(Setingmodel setingmodel) throws Exception {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";

        Map<String, Object> resultmaps = new HashMap<>();
        String faceToken = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", setingmodel.getImgpath());
            map.put("image_type", setingmodel.getImage_Type());
            map.put("user_id", setingmodel.getUserID());
            map.put("group_id", setingmodel.getGroupID());
            map.put("liveness_control", setingmodel.getLiveness_Control());
            map.put("group_id_list", setingmodel.getGroupID());
            map.put("quality_control", setingmodel.getQuality_Control());
            String param = GsonUtils.toJson(map);
            String result = HttpUtil.post(url, token, "application/json", param);
            logger.info("人脸新增结果：{}", result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null != jsonObject) {
                String error_msg = jsonObject.getString("error_msg");
                if ("SUCCESS".equals(error_msg)) {
                    JSONObject result1 = jsonObject.getJSONObject("result");
                    faceToken = result1.getString("face_token");
                } else {
                    throw new ServiceException("人脸识别失败");
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        return faceToken;

    }

    /**
     * 人脸更新
     *
     * @param facaddseting 参数设置
     * @return 返回信息map
     * @throws IOException
     */
    public Map FaceUpdate(Setingmodel facaddseting) throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
        Map resultmap = FaceAddAndUpdate(facaddseting, url);
        return resultmap;

    }

    private Map FaceAddAndUpdate(Setingmodel facaddseting, String url) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(facaddseting.getImgpath()));
        String imagebase64 = Base64Util.encode(String.valueOf(bytes));
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("image", imagebase64);
            map.put("group_id", facaddseting.getGroupID());
            map.put("user_id", facaddseting.getUserID());
            map.put("liveness_control", facaddseting.getLiveness_Control());
            map.put("image_type", facaddseting.getImage_Type());
            map.put("quality_control", facaddseting.getQuality_Control());
            String param = GsonUtils.toJson(map);
            String result = HttpUtil.post(url, token, "application/json", param);
            Map resultmap = GsonUtils.fromJson(result, Map.class);
            return resultmap;
        } catch (Exception e) {
            System.out.println("失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询这个人的面部信息
     *
     * @param setingmodel
     * @return map
     */
    public Map FindPersonFaceList(Setingmodel setingmodel) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist";
        Map<String, Object> map = new HashMap<>();
        if (!map.isEmpty()) {
            map.clear();
        }
        map.put("group_id", setingmodel.getGroupID());
        map.put("user_id", setingmodel.getUserID());
        String param = GsonUtils.toJson(map);

        try {
            String result = HttpUtil.post(url, token, "application/json", param);
            Map resultmap = GsonUtils.fromJson(result, Map.class);
            return resultmap;
        } catch (Exception e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询本组的面部信息
     *
     * @param setingmodel
     * @return
     */
    public Map FindGroupList(Setingmodel setingmodel) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", setingmodel.getGroupID());
        String param = GsonUtils.toJson(map);
        try {
            String result = HttpUtil.post(url, token, "application/json", param);
            Map resultmap = GsonUtils.fromJson(result, Map.class);
            return resultmap;

        } catch (Exception e) {
            System.out.println("未查询到人脸信息");
            e.printStackTrace();
        }
        return null;
    }

    public Map DelPersonFace(Setingmodel setingmodel) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
        Map map = FindPersonFaceList(setingmodel);
        Object result = map.get("result");
        String s = GsonUtils.toJson(result);
        JSONObject jsonObject = JSONObject.parseObject(s);
        String face_token = jsonObject.getString("face_list");
        String substring = face_token.substring(2, face_token.length() - 2);
        String[] split = substring.split("\"");
        face_token = split[7];
        System.out.println(face_token);
        map.put("group_id", setingmodel.getGroupID());
        map.put("user_id", setingmodel.getUserID());
        map.put("face_token", face_token);
        String param = GsonUtils.toJson(map);
        try {
            String result2 = HttpUtil.post(url, token, "application/json", param);
            Map resultmap = GsonUtils.fromJson(result2, Map.class);
            return resultmap;
        } catch (Exception e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 人脸查找
     *
     * @return
     */
    public String FaceSearch(Setingmodel setingmodel) throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
//        byte[] bytes = Files.readAllBytes(Paths.get(setingmodel.getImgpath()));
//        String imagebase64 = Base64Util.encode(bytes);
        String userId = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", setingmodel.getImgpath());
            map.put("liveness_control", setingmodel.getLiveness_Control());
            map.put("group_id_list", setingmodel.getGroupID());
            map.put("image_type", setingmodel.getImage_Type());
            map.put("quality_control", setingmodel.getQuality_Control());
            String param = GsonUtils.toJson(map);
            String result = HttpUtil.post(url, token, "application/json", param);

            logger.info("人脸查询结果：{}", result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null != jsonObject) {
                String error_msg = jsonObject.getString("error_msg");
                if ("SUCCESS".equals(error_msg)) {
                    JSONObject result1 = jsonObject.getJSONObject("result");
                    JSONArray user_list = result1.getJSONArray("user_list");
                    if(null != user_list && user_list.size() > 0){
                        JSONObject o = (JSONObject) user_list.get(0);
                        userId = o.getString("user_id");
                    }

                } else {
                    throw new ServiceException("人脸识别失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

}
