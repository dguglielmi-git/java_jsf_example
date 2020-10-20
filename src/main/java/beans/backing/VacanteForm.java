package beans.backing;

import beans.helper.ColoniaHelper;
import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@RequestScoped
public class VacanteForm {

    // Inyeccion del objeto Candidato
    // Utilizamos @Inject para instanciar un Bean declarado con @Named
    @Inject
    private Candidato candidato;

    private boolean comentarioEnviado;

    // Inyectamos el bean declarado con @Named
    @Inject
    private ColoniaHelper coloniaHelper;

    Logger log = LogManager.getRootLogger();

    public VacanteForm() {
        log.info("Creando el objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

    public ColoniaHelper getColoniaHelper() {
        return coloniaHelper;
    }

    public void setColoniaHelper(ColoniaHelper coloniaHelper) {
        this.coloniaHelper = coloniaHelper;
    }

    public String enviar() {
        if (this.candidato.getNombre().equals("Juan")) {

            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "Gracias pero Juan Perez ya trabaja con nosotros.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null; // este es un mensaje global
                facesContext.addMessage(componentId, facesMessage);
                return "index";
            }
            log.info("Entrando al caso de exito");
            return "exito";
        }
        log.info("Entrando al caso de fallo");
        return "fallo";
    }

    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // con UIViewRoot vamos a buscar un componente dentro de nuestro formulario
        // podremos acceder a cualquier componente de nuestra vista de JSF
        // Para poder acceder a los componentes de la vista de JSF necesitamos
        // asignar un ID a cada uno de los elementos que queramos acceder de
        // nuestra pagina JSF
        UIViewRoot uiViewRoot = facesContext.getViewRoot();

        //  Recuperando el Codigo Postal desde la vista
       // String nuevoCodigoPostal = (String) valueChangeEvent.getNewValue();
       // Como el getNewValue nos va a devolver un Long, recibimos el Long, y luego
       // lo convertimos a un int, solicitando su valor como entero:
       int nuevoCodigoPostal = ((Long) valueChangeEvent.getNewValue()).intValue();
        //  if ("03810".equals(nuevoCodigoPostal)) {
        /*
            UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:colonia");
            String nuevaColonia = "Napoles";
            coloniaInputText.setValue(nuevaColonia);
            coloniaInputText.setSubmittedValue(nuevaColonia);
         */

 /*
        UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
        int coloniaId = 1;
        coloniaIdInputText.setValue(coloniaId);
        coloniaIdInputText.setSubmittedValue(coloniaId);
         */
 
        UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
        int coloniaId = this.coloniaHelper.getColoniaIdPorCP(nuevoCodigoPostal);
        coloniaIdInputText.setValue(coloniaId);
        coloniaIdInputText.setSubmittedValue(coloniaId);

        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        String nuevaCiudad = "Ciudad de Mexico";
        ciudadInputText.setValue(nuevaCiudad);
        ciudadInputText.setSubmittedValue(nuevaCiudad);

        // Con el objeto facesContext indicamos que envie la respuesta
        facesContext.renderResponse();
        //  }
    }

    public void ocultarComentario(ActionEvent actionEvent) {
        this.comentarioEnviado = !this.comentarioEnviado;
    }
}
