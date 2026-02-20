import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String TOKEN = "NO_NECESARIO";

        var iaPregunta = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        var iaRespuesta = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        List<ChatMessage> historyPregunta = new ArrayList<>();
        List<ChatMessage> historyRespuesta = new ArrayList<>();

        historyPregunta.add(new SystemMessage(
                "Eres una IA curiosa que hace preguntas interesantes sobre tecnología."
        ));

        historyRespuesta.add(new SystemMessage(
                "Eres una IA experta que responde de forma clara y didáctica."
        ));

        // IA 1 pregunta
        historyPregunta.add(new UserMessage(
                "Haz una pregunta sobre el futuro de la inteligencia artificial."
        ));
        AiMessage pregunta = iaPregunta.chat(historyPregunta).aiMessage();
        historyPregunta.add(pregunta);

        System.out.println("IA 1 pregunta:");
        System.out.println(pregunta.text());
        System.out.println();

        // IA 2 responde
        historyRespuesta.add(new UserMessage(pregunta.text()));
        AiMessage respuesta = iaRespuesta.chat(historyRespuesta).aiMessage();
        historyRespuesta.add(respuesta);

        System.out.println("IA 2 responde:");
        System.out.println(respuesta.text());
    }
}