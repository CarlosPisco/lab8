package com.example.laboratorio8.Controllers;


import com.example.laboratorio8.Entities.Evento;
import com.example.laboratorio8.Entities.TipoTicketEvento;
import com.example.laboratorio8.Repository.EventoRepository;
import com.example.laboratorio8.Repository.TipodeTicketRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin

public class EventoController {

    final EventoRepository eventoRepository;

    final TipodeTicketRepository tipodeTicketRepository;


    public EventoController(EventoRepository eventoRepository, TipodeTicketRepository tipodeTicketRepository) {
        this.eventoRepository = eventoRepository;

        this.tipodeTicketRepository = tipodeTicketRepository;
    }


    @GetMapping("/evento")
    public List<Evento> listaEventos(){

        return eventoRepository.findAll();
    }


    @GetMapping("/evento/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerEvento (@PathVariable("id") String idStr){
        HashMap<String,Object> responseJson = new HashMap<>();


        try{
            int id = Integer.parseInt(idStr);

            Optional<Evento> eventoOptional = eventoRepository.findById(id);

            if(eventoOptional.isPresent()){
                responseJson.put("resultado","exitoso");
                responseJson.put("evento",eventoOptional.get());
                return ResponseEntity.ok(responseJson);
            }else{
                responseJson.put("msg","Evento no encontrado");
                responseJson.put("resultado","falla");

                return ResponseEntity.badRequest().body(responseJson);
            }




        }catch (Exception NumberFormatException){
            responseJson.put("msg","El ID no debe ser un numero entero positivo");
            responseJson.put("resultado","falla");
            return ResponseEntity.badRequest().body(responseJson);
        }
    }


    @PostMapping("/evento")
    public ResponseEntity<HashMap<String,Object>> crearEvento(@RequestBody Evento evento,@RequestParam(value="fetchId",required = false) boolean fetchId){

        HashMap<String,Object> responseJson = new HashMap<>();

            List<Evento> lista = eventoRepository.findAll();

            int id = lista.toArray().length;
            evento.setId(id+1);
            eventoRepository.save(evento);

            responseJson.put("estado","creado");
            if(fetchId){
                responseJson.put("id",evento.getId());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);



    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){

        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST") || request.getMethod().equals("PUT")){
            responseMap.put("msg","Debe enviar un evento");
            responseMap.put("estado","error");
        }
        return ResponseEntity.badRequest().body(responseMap);

    }


    @GetMapping("/eventoContipoDeTicket/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerTipoTicket (@PathVariable("id") String idStr) {
        HashMap<String, Object> responseJson = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);

            Optional<TipoTicketEvento> tipoTicketEventoOptional = tipodeTicketRepository.findById(id);

            if(tipoTicketEventoOptional.isPresent()){
                TipoTicketEvento tipoTicketEvento = tipoTicketEventoOptional.get();

                List<Evento> listaEventos =  eventoRepository.findAll();
                List<Evento> eventosTicket = new ArrayList<>();

                for(Evento evento1 : listaEventos){
                    if(Objects.equals(tipoTicketEvento.getId(), evento1.getId())){
                        eventosTicket.add(evento1);
                    }
                }

                responseJson.put("resultado","exitoso");
                responseJson.put("eventoConTipoDeTicket",eventosTicket);





                responseJson.put("evento",tipoTicketEventoOptional.get());
                return ResponseEntity.ok(responseJson);
            }else{
                responseJson.put("msg","Tipo de ticket no encontrado");
                responseJson.put("resultado","falla");

                return ResponseEntity.badRequest().body(responseJson);
            }


        }catch (Exception NumberFormatException){
            responseJson.put("msg","El ID no debe ser un numero entero positivo");
            responseJson.put("resultado","falla");
            return ResponseEntity.badRequest().body(responseJson);
        }


    }




}
