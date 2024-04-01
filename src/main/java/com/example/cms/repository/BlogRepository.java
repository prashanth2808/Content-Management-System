package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Blogs;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;

public interface BlogRepository extends JpaRepository<Blogs, Integer> {
	
	boolean existsByTitle(String title);

	boolean existsByUserAndPanel(User user,ContributionPanel panel);
	
}
