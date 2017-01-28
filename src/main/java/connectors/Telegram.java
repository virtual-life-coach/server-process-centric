package connectors;

import vlc.common.to.TelegramMessageTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Telegram {

    private static final String TELEGRAM_BOT_ENDPOINT = "https://vlc-client-telegram.appspot.com/";

    public static boolean sendMessage(String chatId, String message) {
        TelegramMessageTO telegramMessage = new TelegramMessageTO(chatId, message);
        WebTarget resource = ClientBuilder.newClient().target(TELEGRAM_BOT_ENDPOINT + "send");
        Entity<TelegramMessageTO> entity = Entity.entity(telegramMessage, MediaType.APPLICATION_JSON);
        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        Response response = request.post(entity);
        return response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL;
    }

    public static void main(String[] args) {

    }
}
