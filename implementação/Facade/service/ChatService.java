package service;

import entity.Carona;
import entity.Chat;

public class ChatService {
    
    public Chat criarNovoChat(Carona carona) {
        System.out.println("-> ChatService: Criando sala de chat para a Carona " + carona.id + ".");
        // Inicializa a sala de chat para o motorista e futuros passageiros.
        return new Chat("CHAT-" + carona.id);
    }
}