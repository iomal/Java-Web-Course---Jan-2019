package exam.service;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.repository.UserRespository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRespository userRespository, ModelMapper modelMapper) {
        this.userRespository = userRespository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel finById(String id) {
        return this.modelMapper.map(this.userRespository.findById(id),UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {
        return Arrays.asList(modelMapper.map(this.userRespository.findAll(),UserServiceModel[].class));
    }

    @Override
    public UserServiceModel findByName(String username) {
        return this.modelMapper.map(this.userRespository.findByUserName(username),UserServiceModel.class);
    }

    @Override
    public boolean save(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        return this.userRespository.save(user)!=null;
    }
}
