package com.itvedant.Watch.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itvedant.Watch.FileStorageProperties;
import com.itvedant.Watch.DAO.UpdateWatchDAO;
import com.itvedant.Watch.DAO.WatchDAO;
import com.itvedant.Watch.Entity.Watch;
import com.itvedant.Watch.Exception.StorageException;
import com.itvedant.Watch.Repository.WatchRepository;

@Service
public class WatchService {
	
	@Autowired
	WatchRepository watchRepository;
	//final variable
	private final Path rootLocation;
	
	//Constructor => for initializing the final variable
	public WatchService(FileStorageProperties fileStorageProperties) {
		this.rootLocation = Paths.get(fileStorageProperties.getUploadDir());
		
		System.out.println("Hello" + rootLocation);
		
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize directory");
		}
	}
	
	public Watch createWatch(WatchDAO watchDAO) {
		
		Watch watch = new Watch();
		
		watch.setProduct_name(watchDAO.getProduct_name());
		watch.setDescription(watchDAO.getDescription());
		watch.setPrice(watchDAO.getPrice());
		watch.setCategory(watchDAO.getCategory());
		
		this.watchRepository.save(watch);
		
		return watch;
		
	}
	
	public List<Watch> readAllWatch(){
		List<Watch> watch = new ArrayList<Watch>();
		watch = this.watchRepository.findAll();
		return watch;
	}
	
	public Watch readWatchById(Integer id) {
		
		Watch watch = new Watch();
		
		watch = this.watchRepository.findById(id).orElse(null);
		return watch;
		
	}
	
	public Watch updateWatch(Integer id, UpdateWatchDAO updateWatchDAO) {
		Watch watch = new Watch();
		
		watch = this.readWatchById(id);
		
		if(updateWatchDAO.getProduct_name() != null) {
			watch.setProduct_name(updateWatchDAO.getProduct_name());
		}
		if(updateWatchDAO.getDescription() != null) {
			watch.setDescription(updateWatchDAO.getDescription());
		}
		if(updateWatchDAO.getPrice() != null) {
			watch.setPrice(updateWatchDAO.getPrice());
		}
		if(updateWatchDAO.getCategory() != null) {
			watch.setCategory(updateWatchDAO.getCategory());
		}
		
		this.watchRepository.save(watch);
		
		return watch;
	}
	
	public String deleteWatch(Integer id) {
		Watch watch = new Watch();
		
		watch = this.readWatchById(id);
		
		this.watchRepository.delete(watch);
		
		return "Watch Data Deleted";
	}
	
	public String storeFile(Integer id, MultipartFile file) {
		try {
				if(file.isEmpty()) {
				throw new StorageException("Could not store empty file");
			}
				
				Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
				
			try(InputStream inputStream = file.getInputStream()) {
				
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				
			} 
			
			Watch watch = this.watchRepository.findById(id).orElseThrow(
					
					() -> {throw new StorageException("Watch with id" + id + "is not found");}
					);
			
			String fileUploadDir = ServletUriComponentsBuilder.fromCurrentContextPath()
									.path("/watch/download/")
									.path(file.getOriginalFilename())
									.toUriString();
			watch.setImage_url(fileUploadDir);
			
			this.watchRepository.save(watch);
			
		} catch (IOException e) {
			
			throw new StorageException("Could not store file");
			
		}
		
		return "File Stored";
	}
	
	public Resource loadAsResource(String fileName) {
		Path file = rootLocation.resolve(fileName);
		try {
			
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
				
			} else {
				throw new StorageException("Could not read file");
			}
			
		} catch (Exception e) {
			throw new StorageException("Could not read file");
		}
	}
	
	
}
