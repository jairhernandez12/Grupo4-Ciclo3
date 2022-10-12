package com.usa.misiontic.masterclass3.service;


import com.usa.misiontic.masterclass3.entities.Reservation;
import com.usa.misiontic.masterclass3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getProduct(int id){
        return reservationRepository.getReservas(id);
    }
    public Reservation save(Reservation p){
        if(p.getIdReserva()==null){
            return reservationRepository.save(p);
        }else{
            Optional<Reservation> e = reservationRepository.getReservas(p.getIdReserva());
            if(e.isPresent()){
                return p;
            }else{
                return reservationRepository.save(p);
            }
        }
    }
    public Reservation update(Reservation p){
        if(p.getIdReserva()!=null){
            Optional<Reservation> q = reservationRepository.getReservas(p.getIdReserva());
            if(q.isPresent()){
                if(p.getIdReserva()!=null){
                    q.get().setIdReserva(p.getIdReserva());
                }
                if(p.getPalco()!=null){
                    q.get().setPalco(p.getPalco());
                }
                if(p.getCliente()!=null){
                    q.get().setCliente(p.getCliente());
                }
                if(p.getFechainicio()!=null){
                    q.get().setFechainicio(p.getFechainicio());
                }
                if(p.getFechafin()!=null){
                    q.get().setFechafin(p.getFechafin());
                }
                reservationRepository.save(q.get());
                return q.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Reservation>p= reservationRepository.getReservas(id);
        if(p.isPresent()){
            reservationRepository.delete(p.get());
            flag=true;
        }
        return flag;

    }


}
