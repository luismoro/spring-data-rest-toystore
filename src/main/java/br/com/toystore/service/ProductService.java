package br.com.toystore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.exception.InternalServerErrorException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luismoro on 25/05/17.
 */
@Service
public class ProductService {

    public static final String CONVERSATION_API_V1_WORKSPACES = "https://gateway.watsonplatform.net/conversation/api/v1/workspaces";

    public ConversationService getService() {
        ConversationService service = new ConversationService("2017-04-21");
        service.setUsernameAndPassword(getUserWatson(), getPassWatson());
        return service;
    }

    public MessageResponse getMessageResponse(String mesage, ConversationService service, String workspaceId) {
        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText(mesage)
                .build();

        return service
                .message(workspaceId, newMessage)
                .execute();
    }

    public String getWorkspace(String name) throws UnirestException, IOException {
        HttpResponse<JsonNode> responseWorkspaceJson = getWorkspaces();
        if (responseWorkspaceJson.getStatus() != 200) {
            responseWorkspaceJson = createWorkspace();
        }
        if (200 != responseWorkspaceJson.getStatus()) {
            throw new InternalServerErrorException("Error to create Workspace", null);
        }

        LinkedHashMap linkedHashMap = new ObjectMapper().readValue(responseWorkspaceJson.getBody().toString(), LinkedHashMap.class);
        List<Map> workspaces = (List<Map>) linkedHashMap.get("workspaces");
        for (Map workspace : workspaces) {
            if (name.equals(workspace.get("name"))) {
                return workspace.get("workspace_id").toString();
            }
        }
        throw new InternalServerErrorException("Error to create Workspace", null);
    }

    public String getWorkspace() throws UnirestException, IOException {
        HttpResponse<JsonNode> responseWorkspaceJson = getWorkspaces();
        if (responseWorkspaceJson.getStatus() != 200) {
            responseWorkspaceJson = createWorkspace();
        }
        if (200 != responseWorkspaceJson.getStatus()) {
            throw new InternalServerErrorException("Error to create Workspace", null);
        }

        LinkedHashMap linkedHashMap = new ObjectMapper().readValue(responseWorkspaceJson.getBody().toString(), LinkedHashMap.class);
        List<Map> workspaces = (List<Map>) linkedHashMap.get("workspaces");
        return workspaces.get(0).get("workspace_id").toString();
    }

    private HttpResponse<JsonNode> createWorkspace() throws UnirestException {
        return Unirest.post(CONVERSATION_API_V1_WORKSPACES)
                .queryString("version", "2017-05-26")
                .header("Content-Type", "application/json")
                .basicAuth(getUserWatson(), getPassWatson())
                .asJson();
    }

    public HttpResponse<JsonNode> getWorkspaces() throws UnirestException {
        return Unirest.get(CONVERSATION_API_V1_WORKSPACES)
                .queryString("version", "2017-05-26")
                .header("Content-Type", "application/json")
                .basicAuth(getUserWatson(), getPassWatson())
                .asJson();
    }

    public String getUserWatson() {
        Map<String, String> env = System.getenv();
        return env.get("USER_WATSON");
    }

    public String getPassWatson() {
        Map<String, String> env = System.getenv();
        return env.get("PASS_WATSON");
    }
}
