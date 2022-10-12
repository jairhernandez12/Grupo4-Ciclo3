package com.usa.misiontic.masterclass3.service;

import com.usa.misiontic.masterclass3.entities.Cloud;
import com.usa.misiontic.masterclass3.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {

    @Autowired
    private CloudRepository cloudRepository;

    public List<Cloud> getAll(){
        return cloudRepository.getAll();
    }
    public Optional<Cloud> getLibrary(int id){
        return cloudRepository.getCloud(id);
    }
    public Cloud save(Cloud p){
        if(p.getId()==null){
            return cloudRepository.save(p);
        }else{
            Optional<Cloud> e = cloudRepository.getCloud(p.getId());
            if(e.isPresent()){
                return p;
            }else{
                return cloudRepository.save(p);
            }
        }
    }
    public Cloud update(Cloud p){
        if(p.getId()!=null){
            Optional<Cloud> q = cloudRepository.getCloud(p.getId());
            if(q.isPresent()){
                if(p.getName()!=null){
                    q.get().setName(p.getName());
                }
                if(p.getDescription()!=null){
                    q.get().setDescription(p.getDescription());
                }
                if(p.getBrand()!=null){
                    q.get().setBrand(p.getBrand());
                }
                if(p.getYear()!=null){
                    q.get().setYear(p.getYear());
                }
                if(p.getCategory()!=null){
                    q.get().setCategory(p.getCategory());
                }

                cloudRepository.save(q.get());
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
        Optional<Cloud>p= cloudRepository.getCloud(id);
        if(p.isPresent()){
            cloudRepository.delete(p.get());
            flag=true;
        }
        return flag;

    }


}
