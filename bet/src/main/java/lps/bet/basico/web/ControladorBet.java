package lps.bet.basico.web;

import org.springframework.web.servlet.mvc.AbstractController;

public abstract class ControladorBet extends AbstractController {
    protected int nivelMinimoAcesso = 5;

    public int getNivelMinimoAcesso() {
        return nivelMinimoAcesso;
    }

    public void setNivelMinimoAcesso(int nivelMinimoAcesso) {
        this.nivelMinimoAcesso = nivelMinimoAcesso;
    }

}
