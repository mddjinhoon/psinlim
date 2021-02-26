package org.mddjinhoon.demo.other.web;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Template;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.mddjinhoon.demo.other.common.util.WebUtils;
import org.mddjinhoon.demo.other.service.posts.PostsService;
import org.mddjinhoon.demo.other.web.dto.PostsResponseDto;
import org.mddjinhoon.demo.other.web.dto.PostsSaveRequestDto;
import org.mddjinhoon.demo.other.web.dto.PostsUpdateRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    /* 한글 파일 다운로드 */
    @PostMapping("/api/post/print")
    public HttpEntity<byte[]> print(HttpServletRequest request) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        headers.setCacheControl("no-cache");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + WebUtils.getFileName(WebUtils.getBrowser(request), "유속가속 펌프") + ".hwp");

        byte[] binary = null;
        List<Map<String, Object>> sensorItems = new ArrayList<>();
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "1");}});
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "2");}});
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "3");}});
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "4");}});
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "5");}});
        sensorItems.add(new HashMap<String, Object>(){{put("uniqueId", "6");}});
        Map<String, Object> data = new HashMap<>();
        data.put("createDate", "작성일");
        data.put("startDate", "시작일");
        data.put("endDate", "종료일");
        data.put("installPlace", "주소");
        data.put("sensorItems", sensorItems);

        Handlebars handlebars = new Handlebars();
        handlebars.registerHelper("ifCheck", (context, options) -> {
            int check = (Integer) context;
            if(check == 0) {
                return "<P ParaShape=\"20\" Style=\"0\">";
            }else {
                return "<P CulmnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\">";
            }
        });

        try {
            Template template = handlebars.compile("report/test-report");
            File tempFile = new File(System.getProperty("user.dir"), "test" + System.currentTimeMillis());
            FileUtils.writeStringToFile(tempFile, template.apply(data));
            /*
            Scanner scan = new Scanner(tempFile);
            while(scan.hasNextLine()){
                System.out.println(scan.nextLine());
            */
            binary = FileUtils.readFileToByteArray(tempFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HttpEntity<>(binary, headers);
    }
}