package exam.service;

import exam.domain.entities.Gender;
import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.domain.models.service.UserServiceNotFriendsModel;
import exam.domain.models.view.UserPossibleFriendViewModel;
import exam.repository.UserRespository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import javax.inject.Inject;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRespository userRespository, ModelMapper modelMapper) {
        this.userRespository = userRespository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel finById(String id){
        return this.modelMapper.map(this.userRespository.findById(id), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll(){
        return Arrays.asList(modelMapper.map(this.userRespository.findAll(), UserServiceModel[].class));
    }

    @Override
    public List<UserServiceNotFriendsModel> findAllPossibleFriends(String id){
        List<UserServiceNotFriendsModel> notFriends = Arrays.asList(modelMapper.map(this.userRespository.notFriends(id), UserServiceNotFriendsModel[].class));
        return notFriends;
    }

    @Override
    public UserServiceModel findByName(String username){
        return this.modelMapper.map(this.userRespository.findByUserName(username), UserServiceModel.class);
    }

    @Override
    public boolean save(UserServiceModel userServiceModel){
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        return this.userRespository.save(user) != null;
    }

    @Override
    public void addFriend(String id, String friendId){
        this.userRespository.addFriend(id, friendId);
    }

    @Override
    public void unfriend(String id, String unfriendId){
        this.userRespository.unfriend(id, unfriendId);
    }
}
