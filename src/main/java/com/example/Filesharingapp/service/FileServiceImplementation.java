package com.example.Filesharingapp.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Filesharingapp.entity.FileEntity;
import com.example.Filesharingapp.exception.FileNotFoundException;
import com.example.Filesharingapp.model.FileModel;
import com.example.Filesharingapp.repository.FileRepository;





@Service
public class FileServiceImplementation implements FileService {


// dependency injection

    @Autowired
    private FileRepository fileRepository;


    private FileModel convertToModel(FileEntity entity){
        FileModel model = new FileModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    





	@Override
	public List<FileModel> getAllFiles() {
		List<FileEntity> entities = fileRepository.findAll();
        return entities.stream().map(this::convertToModel).collect(Collectors.toList());
	}



    @Override
    public ResponseEntity<?> deleteFile(int id) {
        Optional<FileEntity> entity = fileRepository.findById(id);
        if(entity.isPresent()){
            fileRepository.deleteById(id);
            return ResponseEntity.ok().body("File deleted successfully");
        }
        else{
            throw new FileNotFoundException("File not found with id: "+id);
        }
	}


    @Override
	public ResponseEntity<?> shareFile(int id) {
        Optional<FileEntity> entity = fileRepository.findById(id);
        if(entity.isPresent()){
            return ResponseEntity.ok().body(convertToModel(entity.get()));
        }
        else{
            throw new FileNotFoundException("File not found with id: "+id);
        }
	}

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file, String uploadedBy) throws IOException {
        FileEntity entity = new FileEntity();
        entity.setFileName(file.getOriginalFilename());
        entity.setUploadedBy(uploadedBy);
        entity.setExpiryTime(LocalDateTime.now().plusDays(1));
        entity.setUploadedTime(LocalDateTime.now());
        entity.setFileData(file.getBytes());
        fileRepository.save(entity);
        return ResponseEntity.ok().body(convertToModel(entity));
	}



   @Override
    public ResponseEntity<?> getFile(int id)  {
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(id);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            FileModel fileModel = new FileModel();
            BeanUtils.copyProperties(fileEntity, fileModel);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileModel.getFileData());
        } else {
            throw new FileNotFoundException("File not found");
        }
    }


@Override
@Scheduled(cron = "0 0 * * * *")
public void deleteExpiredFiles() {
    List<FileEntity> entities = fileRepository.findByExpiryTimeBefore(LocalDateTime.now());
    entities.forEach(fileRepository::delete);
    System.out.println("Expired files deleted successfully"+LocalDateTime.now());
}

}
