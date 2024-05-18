package com.zeco.zecomedical.fileUploads.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zeco.zecomedical.dto.RequestResponse;
import com.zeco.zecomedical.fileUploads.dto.FileUploadResponse;
import com.zeco.zecomedical.fileUploads.dto.FileUploadRequest;
import com.zeco.zecomedical.general.repositories.UsersRepository;
import com.zeco.zecomedical.general.utils.FindingUsers;
import com.zeco.zecomedical.general.utils.MyDebug;
import com.zeco.zecomedical.model.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileUploadService {

    private final Cloudinary cloudinary;
    private final UsersRepository usersRepository;
    private final FindingUsers findingUsers;



    public String upload(MultipartFile file, String folderName){
        try{

            //options, check docs to see all the options ,check under the upload Api
            Map params1 = ObjectUtils.asMap(
                    //"use_filename", true,
                    //"unique_filename", false,
                    "overwrite", true,
                    "folder", folderName
            );

            //upload returns a map of key pair values, and url is one the keys, check cloudinary docs and check response to see all the key pair values
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params1);
           String url =  uploadResult.get("url").toString();


           return  url;

        }catch (IOException e){
            log.error(e);
        }

        return  null;
    }




    public FileUploadResponse uploadAndSaveUrl(MultipartFile file) {

        //MAX FILE SIZE is 1mb..  i have set the max size in the properties file

      FileUploadResponse res = new FileUploadResponse();



        Users user = findingUsers.findUserByTheUsername("user not found, can not upload image");

        if(file.isEmpty()){

            res.setStatus(HttpStatus.BAD_REQUEST.value());
            res.setMessage("where is the picture!!!!");
            res.setRole(null);

            return res;
        }

       String url = upload(file, "userProfiles");

        if(url != null){

            user.setProfilePhotoUrl(url);
            usersRepository.save(user);

            res.setStatus(HttpStatus.OK.value());
            res.setMessage("picture saved");
            res.setRole(user.getRole());

            return res;

        }

        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setMessage("errrrrrrrrrrr");
        res.setRole(null);

        return res;



    }

}
