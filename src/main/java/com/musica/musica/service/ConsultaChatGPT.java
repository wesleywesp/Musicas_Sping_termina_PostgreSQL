package com.musica.musica.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
        public static String obterInformacao(String texto) {
            try {
                OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

                CompletionRequest requisicao = CompletionRequest.builder()
                        .model("gpt-3.5-turbo-instruct")
                        .prompt("Informaçoes sobre o artista: " + texto)
                        .maxTokens(1000)
                        .temperature(0.7)
                        .build();

                var resposta = service.createCompletion(requisicao);
                return resposta.getChoices().get(0).getText();
            }catch (Exception e){
                e.printStackTrace();
                return "Erro ao obter a tradução";
            }
        }
}
