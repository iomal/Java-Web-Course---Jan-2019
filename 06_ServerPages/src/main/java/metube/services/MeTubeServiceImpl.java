package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repositories.MeTubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.*;

public class MeTubeServiceImpl implements MeTubeSerivce {
    private final MeTubeRepository meTubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public MeTubeServiceImpl(MeTubeRepository meTubeRepository, ModelMapper modelMapper) {
        this.meTubeRepository = meTubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.meTubeRepository.save(tube);
    }

    @Override
    public List<TubeServiceModel> getAll() {
        List<Tube> tubes = this.meTubeRepository.findAll();
        return Arrays.asList(modelMapper.map(tubes, TubeServiceModel[].class));
    }

    @Override
    public Optional<TubeServiceModel> findById(String id) {

        Optional<Tube> tube = this.meTubeRepository.findById(id);
        Optional<TubeServiceModel> tubeServiceModel=null;
        if (tube != null) {
            tubeServiceModel = Optional.ofNullable(modelMapper.map(tube.get(), TubeServiceModel.class));
            return tubeServiceModel;
        }
        else {

        return tubeServiceModel;}
    }
}
