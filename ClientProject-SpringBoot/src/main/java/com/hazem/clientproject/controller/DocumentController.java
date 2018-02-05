package com.hazem.clientproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.hazem.clientproject.entity.Document;
import com.hazem.clientproject.service.IDocumentService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DocumentController {

	@Autowired
	private IDocumentService documentService;
	@GetMapping("upload")
	public ResponseEntity<Document> getDocumentById(@RequestParam("id") String id) {
		Document document = documentService.getDocumentById(Integer.parseInt(id));
		return new ResponseEntity<Document>(document, HttpStatus.OK);
	}
	@GetMapping("all-documents")
	public ResponseEntity<List<Document>> getAllDocuments() {
		List<Document> list = documentService.getAllDocuments();
		return new ResponseEntity<List<Document>>(list, HttpStatus.OK);
	}
	@PostMapping("document")
	public ResponseEntity<Void> createDocument(@RequestBody Document document, UriComponentsBuilder builder) {
		boolean flag = documentService.createDocument(document);
		if (flag == false) {
		     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/document?id={id}").buildAndExpand(document.getIdDocument()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("document")
	public ResponseEntity<Document> updateDocument(@RequestBody Document document) {
		documentService.updateDocument(document);
		return new ResponseEntity<Document>(document, HttpStatus.OK);
	}
	@DeleteMapping("document")
	public ResponseEntity<Void> deleteDocument(@RequestParam("id") String id) {
		documentService.deleteDocument(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	@PostMapping("upload")
	 public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

		 if (file.isEmpty()) {
			 redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			 return "Error";
		 }

		 try {
			 
			 // Get the file and save it somewhere
			 byte[] bytes = file.getBytes();
			 Path path = Paths.get("ressources/" + file.getOriginalFilename());
			 Files.write(path, bytes);

			 redirectAttributes.addFlashAttribute("message",
					 "You successfully uploaded '" + file.getOriginalFilename() + "'");

		 } catch (IOException e) {
			 e.printStackTrace();
			 return "Error";
		 }	 
		 return "Success";	 }

}
