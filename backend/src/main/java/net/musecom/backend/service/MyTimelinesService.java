package net.musecom.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.musecom.backend.entity.MyTimelines;
import net.musecom.backend.repository.MyTimelinesRepository;

@Service
public class MyTimelinesService {

    @Autowired
    private MyTimelinesRepository myTimelinesRepository;

    //save
    public MyTimelines saveMyTimelines(MyTimelines myTimelines){
        return myTimelinesRepository.save(myTimelines);
    }

    //update
    public Optional<MyTimelines> updateMyTimelines(Long id, MyTimelines updateMyTimelines){
        return myTimelinesRepository.findById(id).map(timelines -> {
            timelines.setJobtitle(updateMyTimelines.getJobtitle());
            timelines.setWdate(updateMyTimelines.getWdate());
            timelines.setWheres(updateMyTimelines.getWheres());
            return myTimelinesRepository.save(timelines);
        });
    }

    //delete
    public boolean deleteMyTimelines(Long id){
        if(myTimelinesRepository.existsById(id)){
            myTimelinesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //list
    public List<MyTimelines> getAllMyTimelines(){
        return myTimelinesRepository.findAll();
    }
}
