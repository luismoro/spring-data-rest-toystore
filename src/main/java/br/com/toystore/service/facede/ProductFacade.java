package br.com.toystore.service.facede;

import br.com.toystore.service.ProductService;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.exception.InternalServerErrorException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by luismoro on 26/05/17.
 */
@Service
public class ProductFacade {

    @Autowired
    ProductService productService;

    public MessageResponse getMessageResponse(String mesage,String workspace) throws UnirestException, IOException, InternalServerErrorException {

        String workspaceId = new String();
        ConversationService service = productService.getService();

        if (workspace == null) {
            workspaceId = productService.getWorkspace();
        }
        else {
            workspaceId = productService.getWorkspace(workspace);
        }

        return productService.getMessageResponse(mesage, service, workspaceId);
    }

}
