package br.mgobo.jsf.app.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtilities {
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
