package com.syf.interview.imageresource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syf.interview.imageresource.entity.ImageDetail;

public interface ImageDetailRepository extends JpaRepository<ImageDetail, Long> {
	ImageDetail findByImageName(String imageName);
}
