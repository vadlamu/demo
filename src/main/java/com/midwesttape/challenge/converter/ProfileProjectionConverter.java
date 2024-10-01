package com.midwesttape.challenge.converter;

import com.midwesttape.challenge.model.IProfileProjection;
import com.midwesttape.challenge.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProfileProjectionConverter {

    //Probably usage of mapstruct would have been ideal, somehow it didn't worked.
    public User fromProjection(IProfileProjection projection) {
        User user = new User();
        user.setNickName(projection.getNickName());
        user.setBio(projection.getBio());

        return user;
    }

}
