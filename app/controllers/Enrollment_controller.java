package controllers;

import controllers.utils.Response;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Enrollment;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.utils.ApiCalls;
import scala.util.parsing.json.JSONObject;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Enrollment_controller extends Controller {

    Enrollment en = new Enrollment();
    ApiCalls apiCalls = new ApiCalls();
    String url = "http://dev.hecbill.hecticus.com";
    String url_enrollment ;

    public Result findAll() {

        ObjectNode customer = Json.newObject();
        ObjectNode tdc = Json.newObject();
        ObjectNode request = Json.newObject();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");


        JsonNode responsebody = Json.newObject();

        // se obtienen todos los datos para el enrollment en una lista
        List<Enrollment> list = en.get_all();
        // se recorre la lista y se realiza la insercion en customer
        for(int i = 0; i<list.size() ; i++){

            // armamos el customer
            customer.put("identifier_customer",list.get(i).getDni());
            customer.put("name_customer",list.get(i).getNombre());
            customer.put("lastName_customer",list.get(i).getApellido());
            customer.put("phoneNumber_customer",list.get(i).getTelefono());
            customer.put("adress_customer",list.get(i).getDireccion());
            customer.put("email_customer",list.get(i).getEmail());
            customer.put("birthDate_customer",formatter.format(list.get(i).getFecha_nacimiento()));
            customer.put("dateAdmission_customer",formatter.format(list.get(i).getFecha_admision()));
            customer.put("dateExpiration_customer",formatter.format(list.get(i).getFecha_expiracion()));
            customer.put("numContrac_customer",list.get(i).getNumero_contrato());
            customer.put("code_customer",list.get(i).getCodigo());

            // armamos la tdc

            tdc.put("numberCreditCard",list.get(i).getNumer_tarjeta());
            tdc.put("nameHolderCreditCard",list.get(i).getNombre_propietario());
            tdc.put("expMonthCreditCard",list.get(i).getMes_expiracion());
            tdc.put("expYearCreditCard",list.get(i).getAno_expiracion());

            // armamos el objeto que va en la peticion a customer
            request.set("customer",customer);
            request.set("creditcard",tdc);


            try {   // realizamos la peticion a customer
                // armamos la url para el enrollment
                url_enrollment = url + "/hecbill/partner/" + String.valueOf(list.get(i).getId_partner()) + "/branch/" + String.valueOf(list.get(i).getId_branch()) + "/service/" + String.valueOf(list.get(i).getId_service()) + "/plan/" + String.valueOf(list.get(i).getId_plan()) + "/customerplan";
                responsebody = ApiCalls.instance().JsonResponsePOST(url_enrollment, request,list.get(i).getToken());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // marcamos como procesado el enrollment
            en = en.findById(list.get(i).getId());
            en.setStatus(true);
            en.update(en);

            System.out.println("DNI customer: " +  list.get(i).getDni());
            System.out.println(responsebody);


        }

        return Response.all_ok();
    }

}
