package beans.helper;

import beans.model.Colonia;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@RequestScoped
@Named
public class ColoniaHelper {

    public List<Colonia> getColonias() {
        List<Colonia> colonias = new ArrayList<>();

        int coloniaId = 1;
        Colonia colonia = new Colonia(coloniaId++, "Napoles", 3810);
        colonias.add(colonia);

        colonia = new Colonia(coloniaId++, "Polanco", 11530);
        colonias.add(colonia);

        colonia = new Colonia(coloniaId++, "Del Valle Centro", 3100);
        colonias.add(colonia);

        return colonias;
    }

    public int getColoniaPorNombre(String nombreColonia) {
        int coloniaId = 0;
        List<Colonia> colonias = this.getColonias();

        for (Colonia colonia : colonias) {
            if (colonia.getNombreColonia().equals(nombreColonia)) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }

        return coloniaId;

    }

    public int getColoniaIdPorCP(int codigoPostal) {
        int coloniaId = 0;
        List<Colonia> colonias = this.getColonias();

        for (Colonia colonia : colonias) {
            if (colonia.getCodigoPostal() == codigoPostal) {
                coloniaId = colonia.getColoniaId();
                break;
            }
        }

        return coloniaId;
    }

    /**
     * Con este metodo vamos a recuperar cada uno de los objetos de tipo colonia
     * pero convirtiendolos al tipo SelectItems, para poder agregarlos
     * directamente a nuestra pagina de index.xhtml y ya no tener que hacer las
     * conversiones en otro lado, sino que esta clase se va a encargar los
     * objetos SelectItem por cada uno de los objetos del tipo Colonia
     */
    public List<SelectItem> getSelectItems() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Colonia> colonias = this.getColonias();

        for (Colonia colonia : colonias) {
            SelectItem selectItem = new SelectItem(colonia.getColoniaId(),
                    colonia.getNombreColonia());
            selectItems.add(selectItem);
        }
        
        return selectItems;
    }
}
