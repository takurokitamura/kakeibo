package com.example.demo.controller;
//
//import com.example.demo.entity.Account;
//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 一括アップロードのコントローラー
// */
//@RestController
//@RequestMapping("file/upload")
//public class AccountProfileImageUploadController {
//    /**
//     * 一括アップロードを行う
//     *
//     * @param multipartFile
//     * @param fileType
//     * @param registrationType
//     * @param dailyAttendanceYyyymm
//     * @param loginUser
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public Object post(
//            @RequestParam("upload_file") MultipartFile multipartFile,
//            @RequestParam("filetype") String fileType,  // ファイル種類
//            @AuthenticationPrincipal  Account loginUser) {  // 認証ユーザー情報
//
//        // ファイルが空の場合は異常終了
//        if (multipartFile.isEmpty()) {
//            // 異常終了時の処理
//        }
//
//        // ファイル種類から決まる値をセットする
//        StringBuffer filePath = new StringBuffer("/uploadfile")
//                .append(File.separator).append(fileType);   //ファイルパス
//
//        // アップロードファイルを格納するディレクトリを作成する
//        File uploadDir = mkdirs(filePath);
//
//        try {
//            // アップロードファイルを置く
//            File uploadFile =
//                    new File(uploadDir.getPath() + "/" + fileType);
//            byte[] bytes = multipartFile.getBytes();
//            BufferedOutputStream uploadFileStream =
//                    new BufferedOutputStream(new FileOutputStream(uploadFile));
//            uploadFileStream.write(bytes);
//            uploadFileStream.close();
//
//            return "You successfully uploaded.";
//        } catch (Exception e) {
//            // 異常終了時の処理
//        } catch (Throwable t) {
//            // 異常終了時の処理
//        }
//    }
//
//
//
//    /**
//     * アップロードファイルを格納するディレクトリを作成する
//     *
//     * @param filePath
//     * @return
//     */
//    private File mkdirs(StringBuffer filePath){
//        Date now = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        File uploadDir = new File(filePath.toString(), sdf.format(now));
//        // 既に存在する場合はプレフィックスをつける
//        int prefix = 0;
//        while(uploadDir.exists()){
//            prefix++;
//            uploadDir =
//                    new File(filePath.toString() + sdf.format(now) + "-" + String.valueOf(prefix));
//        }
//
//        // フォルダ作成
//        uploadDir.mkdirs();
//
//        return uploadDir;
//    }
//}


import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * アカウントプロフィール画像アップロードコントローラ。
 */
@Controller
@RequestMapping(value = "/account/upload")
public class AccountProfileImageUploadController {

    /** HTTPセッション */
    private final HttpSession session;
    /** セッションキー(ログインユーザのアカウント) */
    private static final String SESSION_FORM_ID = "account";
    /** アプリケーション環境設定 */
    private Environment environment;

    @Autowired
    public AccountProfileImageUploadController(HttpSession session, Environment environment) {
        this.session = session;
        this.environment = environment;
    }

    @RequestMapping(path = "/init")
    String init(Model model) {
        return "account/accountProfileImageUploadForm";
    }


    @RequestMapping(path = "/upload")
    String upload(Model model, @RequestParam("upload_file") MultipartFile multipartFile) {
        // ファイルが空の場合は異常終了
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("empty file.");
        }

        // サイズが1Mを超えるファイルはエラー
        if (multipartFile.getSize() > 1048576) {
            throw new RuntimeException("file size over.");
        }

        // ContentTypeを取得し「images/」以外をエラー
        if( !multipartFile.getContentType().contains("image/")){
            throw new RuntimeException("file type is error.");
        }

        // プロフィール画像を格納するディレクトリの作成
        String dirPath = environment.getProperty("upload.dir.path");
        StringBuffer filePath = new StringBuffer(dirPath);
        File uploadDir = mkdirs(filePath);

        Account account = (Account) session.getAttribute(SESSION_FORM_ID);
        File uploadFile = new File(uploadDir.getPath() + "/" + account.getId() + "_profile");
        // アカウントに紐づくプロフィール画像が既に存在していたら、削除する。
        if (uploadFile.exists()){
            uploadFile.delete();
        }

        // プロフィール画像を配置する。
        try {
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
            uploadFileStream.write(bytes);
            uploadFileStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "account/accountProfileImageUploadForm";
    }

    /**
     * アップロードファイルを格納するディレクトリを作成する
     *
     * @param filePath ファイルパス
     * @return File
     */
    private File mkdirs(StringBuffer filePath) {
        File uploadDir = new File(filePath.toString());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir;
    }
}
