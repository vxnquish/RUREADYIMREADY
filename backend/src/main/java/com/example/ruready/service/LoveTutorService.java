package com.example.ruready.service;

import com.google.gson.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoveTutorService {

    public String getAdvice(String message) {
        return getAdviceFromTogether(message); // Groq 또는 Together 중 하나로 선택
    }

    @Value("${externals.grog.apikey}")
    private String externalGrogApiKey;

    @Value("${externals.grog.model}")
    private String externalGrogModel;

    public String getAdviceFromGroq(String message) {
        OkHttpClient client = new OkHttpClient();

        String apiKey = externalGrogApiKey;
        String model = externalGrogModel;

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", model);
        requestBody.addProperty("stream", false); // Groq은 명시적으로 stream: false가 필요할 수 있음

        JsonArray messages = new JsonArray();

        JsonObject system = new JsonObject();
        system.addProperty("role", "system");
        system.addProperty("content", "너는 연애 상담 전문가야. 고민에 공감하며 현실적인 연애 조언을 짧고 따뜻하게 해줘.");
        messages.add(system);

        JsonObject user = new JsonObject();
        user.addProperty("role", "user");
        user.addProperty("content", message);
        messages.add(user);

        requestBody.add("messages", messages);

        Request request = new Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("📥 Groq 응답 원문: " + responseBody); // 디버깅 출력

            if (response.isSuccessful()) {
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                return json.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("message")
                        .get("content").getAsString().trim();
            } else {
                return "❗️ Groq 응답 오류: " + response.code() + " - " + responseBody;
            }
        } catch (IOException e) {
            return "❗️ 네트워크 오류 발생: " + e.getMessage();
        }
    }

    @Value("${externals.together.apikey}")
    private String externalTogetherApiKey;

    @Value("${externals.together.model}")
    private String externalTogetherModel;

    public String getAdviceFromTogether(String message) {
        OkHttpClient client = new OkHttpClient();

        String apiKey = externalTogetherApiKey;
        String model = externalTogetherModel;

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", model);

        JsonArray messages = new JsonArray();

        JsonObject system = new JsonObject();
        system.addProperty("role", "system");
        system.addProperty("content", "너는 연애 상담 전문가야. 공감하면서 현실적이고 따뜻한 조언을 해줘.");
        messages.add(system);

        JsonObject user = new JsonObject();
        user.addProperty("role", "user");
        user.addProperty("content", message);
        messages.add(user);

        requestBody.add("messages", messages);

        Request request = new Request.Builder()
                .url("https://api.together.xyz/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("📥 Together 응답 원문: " + responseBody);

            if (response.isSuccessful()) {
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                return json.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("message")
                        .get("content").getAsString().trim();
            } else {
                return "❗️ Together 응답 오류: " + response.code() + " - " + responseBody;
            }
        } catch (IOException e) {
            return "❗️ 네트워크 오류 발생: " + e.getMessage();
        }
    }

}