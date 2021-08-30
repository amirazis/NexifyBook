package com.nexify.book.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

	private Long id;
	private String author;
	private String title;
}
