package service;

import entity.Carona;

public class NotificacaoService {
    
    public void enviarNotificacaoSucesso(String destinatario, Carona carona) {
        System.out.println("-> NotificacaoService: Enviando notificação para " + destinatario + ".");
        System.out.println("   [ALERTA] Carona " + carona.id + " publicada com sucesso.");
    }
    
    public void enviarAlertaPassageiros(Carona carona) {
        System.out.println("-> NotificacaoService: Alertando passageiros sobre nova carona disponível de " + carona.origem + ".");
    }
}