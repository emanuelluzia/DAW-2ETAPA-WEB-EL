package br.edu.ifsul.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterAutor")
public class ConverterAutor implements Serializable, Converter {

    @Override // método que converte da tela para o objeto
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        try {
            return EntityManagerUtil.getEntityManager().find(Autor.class, Integer.parseInt(string));
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Autor obj = (Autor) o;
        return obj.getId().toString();
    }

}
