package com.itvedant.Watch.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.Watch.DAO.UpdateWatchDAO;
import com.itvedant.Watch.DAO.WatchDAO;
import com.itvedant.Watch.Service.WatchService;

@Controller
@RequestMapping("/watch")
public class WatchController {
	
	@Autowired
	WatchService watchService;
	
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody WatchDAO watchDAO){
		return ResponseEntity.ok(this.watchService.createWatch(watchDAO));
		
	}
	
	@GetMapping("")
	public ResponseEntity<?> readAll(){
		return ResponseEntity.ok(this.watchService.readAllWatch());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readWatchById(@PathVariable Integer id){
		return ResponseEntity.ok(this.watchService.readWatchById(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateWatchDAO updateWatchDAO){
		return ResponseEntity.ok(this.watchService.updateWatch(id, updateWatchDAO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		return ResponseEntity.ok(this.watchService.deleteWatch(id));
	}
	@PostMapping("/{id}/upload")
	public ResponseEntity<?> upload(@PathVariable Integer id,@RequestBody MultipartFile file){
		return ResponseEntity.ok(this.watchService.storeFile(id, file));
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> download(@PathVariable String fileName){
		Resource resource = this.watchService.loadAsResource(fileName);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
				"atachment; filename\"" + fileName + "\"").body(resource);
	}

}
