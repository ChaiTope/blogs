package net.musecom.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.musecom.backend.entity.PostFile;
import net.musecom.backend.service.PostFileService;


@RestController
@RequestMapping("/api/posts/files")
public class PostFileController {

    @Autowired
    private PostFileService postFileService;

    private static final List<String> ALLOWED_IMAGE_EXT = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");
    private static final String UPLOAD_DIR = "C:/ChaiYunSung/spring_works/blogs/frontend/public/upload/";

    //업로드
    @PostMapping
    public ResponseEntity<?> uploadFile(
        @RequestParam("ntime") Long ntime,
        @RequestParam("files") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일이 없습니다.");
        }try{
            //파일명 및 확장자 분리
            String originalFileName = file.getOriginalFilename();
            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

            //이미지인지 일반파일인지 확인
            boolean isImg = ALLOWED_IMAGE_EXT.contains(ext);
            String subDirectory = isImg ? "images" : "files";

            //새 파일명
            String uuid = UUID.randomUUID().toString();//임의의 문자 생성
            String newFilename = subDirectory + "_" + uuid + "." + ext;

            //업로드
            Path uploadPath = Paths.get(UPLOAD_DIR + subDirectory + "/" + ntime.toString());
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            //파일 저장
            Path filePath = uploadPath.resolve(newFilename);
            Files.write(filePath, file.getBytes());
            
            //DB에 저장
            PostFile postFile = new PostFile();
            postFile.setNtime(ntime);
            postFile.setNfilename(newFilename);
            postFile.setOfilename(originalFileName);
            postFile.setExt(ext);
            postFile.setFsize(file.getSize());

            postFileService.saveFile(postFile);

            //응답
            Map<String, Object> res = new HashMap<>();
            res.put("url", "/upload/" + subDirectory + "/" + ntime + "/" + newFilename);
            return ResponseEntity.ok(res);

        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 중 오류가 발생했습니다..");
        }
    }

    //아이디로 가져오기

    //ntime으로 가져오기

    //파일 수정

    //파일 삭제
}
