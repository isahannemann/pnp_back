package com.peaktech.pnp.api.service.upload;

import com.peaktech.pnp.model.input.FileUploadInput;
import com.peaktech.pnp.model.output.photo.PhotoOutput;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.peaktech.pnp.enums.UserType.*;

@Service
public class UserUploadService {

    private final TutorUploadStrategy tutorStrategy;
    private final PetUploadStrategy petStrategy;
    private final HostUploadStrategy hostStrategy;

    @Autowired
    public UserUploadService(TutorUploadStrategy tutorStrategy,
                             PetUploadStrategy petStrategy,
                             HostUploadStrategy hostStrategy) {
        this.tutorStrategy = tutorStrategy;
        this.petStrategy = petStrategy;
        this.hostStrategy = hostStrategy;
    }

    public String saveUserPhoto(FileUploadInput request, UserType userType) {
        UploadStrategy strategy = getUploadStrategy(userType);
        return strategy.saveFile(request);
    }

    public PhotoOutput getUserPhoto(String filename, UserType userType) throws IOException {
        UploadStrategy strategy = getUploadStrategy(userType);
        return strategy.getFile(filename);
    }

    public boolean deleteUserPhoto(String filename, UserType userType) {
        UploadStrategy strategy = getUploadStrategy(userType);
        return strategy.deleteFile(filename);
    }

    private UploadStrategy getUploadStrategy(UserType userType) {
        switch (userType) {
            case TUTOR: return tutorStrategy;
            case PET: return petStrategy;
            case HOST: return hostStrategy;
            default: throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }
}
