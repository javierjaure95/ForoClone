package com.foroclone.foroclone.config;

import com.foroclone.foroclone.domain.dto.*;
import com.foroclone.foroclone.domain.entities.*;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserCreateService userCreateService,
                               Mapper<UserEntity, UserCreateDto> userCreateMapper,
                               Mapper<UserEntity, UserDto> userMapper, 

                               CommunityService communityService,
                               Mapper<CommunityEntity, CommunityDto> communityMapper,

                               PostService postService,
                               Mapper<PostEntity, PostDto> postMapper,

                               CommentService commentService,
                               Mapper<CommentEntity, CommentDto> commentMapper,

                               UpvoteService upvoteService,
                               Mapper<UpvoteEntity, UpvoteDto> upvoteMapper) {
        return args -> {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // -------- USUARIOS --------
            if (userCreateService.count() == 0) {
                UserCreateDto user1 = new UserCreateDto();
                user1.setUsername("aliceW");
                user1.setEmail("alice@example.com");
                user1.setPassword(passwordEncoder.encode("password123"));
                UserEntity savedUser1 = userCreateService.save(userCreateMapper.mapFrom(user1));
                UserDto savedUserDto1 = userMapper.mapTo(savedUser1);

                UserCreateDto user2 = new UserCreateDto();
                user2.setUsername("bobBuilder");
                user2.setEmail("bob@example.com");
                user2.setPassword(passwordEncoder.encode("password123"));
                UserEntity savedUser2 = userCreateService.save(userCreateMapper.mapFrom(user2));
                UserDto savedUserDto2 = userMapper.mapTo(savedUser2);

                UserCreateDto user3 = new UserCreateDto();
                user3.setUsername("charlieTech");
                user3.setEmail("charlie@example.com");
                user3.setPassword(passwordEncoder.encode("password123"));
                UserEntity savedUser3 = userCreateService.save(userCreateMapper.mapFrom(user3));
                UserDto savedUserDto3 = userMapper.mapTo(savedUser3);

                UserCreateDto user4 = new UserCreateDto();
                user4.setUsername("dianaDev");
                user4.setEmail("diana@example.com");
                user4.setPassword(passwordEncoder.encode("password123"));
                UserEntity savedUser4 = userCreateService.save(userCreateMapper.mapFrom(user4));
                UserDto savedUserDto4 = userMapper.mapTo(savedUser4);

                UserCreateDto user5 = new UserCreateDto();
                user5.setUsername("xaviDev");
                user5.setEmail("xavi@example.com");
                user5.setPassword(passwordEncoder.encode("password123"));
                userCreateService.save(userCreateMapper.mapFrom(user5));
                

                // -------- COMUNIDADES --------
                CommunityDto community1 = new CommunityDto();
                community1.setCreatedAt(LocalDateTime.now());
                community1.setName("technology");
                community1.setTitle("Technology & Development");
                community1.setDescription("A community for tech lovers");
                community1.setCreator(savedUserDto1);
                CommunityEntity savedCommunity1 = communityService.save(communityMapper.mapFrom(community1));
                CommunityDto savedCommunityDto1 = communityMapper.mapTo(savedCommunity1);

                CommunityDto community2 = new CommunityDto();
                community2.setCreatedAt(LocalDateTime.now());
                community2.setName("movies");
                community2.setTitle("Film & Cinema");
                community2.setDescription("Discuss the latest movies, series, and everything cinema");
                community2.setCreator(savedUserDto2);
                CommunityEntity savedCommunity2 = communityService.save(communityMapper.mapFrom(community2));
                CommunityDto savedCommunityDto2 = communityMapper.mapTo(savedCommunity2);

                CommunityDto community3 = new CommunityDto();
                community3.setCreatedAt(LocalDateTime.now());
                community3.setName("gaming");
                community3.setTitle("Gaming Hub");
                community3.setDescription("All things games: news, reviews, discussions.");
                community3.setCreator(savedUserDto3);
                CommunityEntity savedCommunity3 = communityService.save(communityMapper.mapFrom(community3));
                CommunityDto savedCommunityDto3 = communityMapper.mapTo(savedCommunity3);

                CommunityDto community4 = new CommunityDto();
                community4.setCreatedAt(LocalDateTime.now());
                community4.setName("books");
                community4.setTitle("Bookworms");
                community4.setDescription("Share and discuss your favorite books!");
                community4.setCreator(savedUserDto4);
                CommunityEntity savedCommunity4 = communityService.save(communityMapper.mapFrom(community4));
                CommunityDto savedCommunityDto4 = communityMapper.mapTo(savedCommunity4);

                CommunityDto community5 = new CommunityDto();
                community5.setCreatedAt(LocalDateTime.now());
                community5.setName("teach");
                community5.setTitle("teachworms");
                community5.setDescription("Share and discuss your favorite books!");
                community5.setCreator(savedUserDto4);
                communityService.save(communityMapper.mapFrom(community5));

                // -------- POSTS --------
                PostDto post1 = new PostDto();
                post1.setCreatedAt(LocalDateTime.now());
                post1.setTitle("Java 21 Features Discussion");
                post1.setContent("Has anyone tried Java 21 features?");
                post1.setCreator(savedUserDto1);
                post1.setCommunity(savedCommunityDto1);
                PostEntity savedPost1 = postService.save(postMapper.mapFrom(post1));

                PostDto post2 = new PostDto();
                post2.setCreatedAt(LocalDateTime.now());
                post2.setTitle("Best Sci-fi Movie of 2024?");
                post2.setContent("What was the best sci-fi movie you watched this year?");
                post2.setCreator(savedUserDto2);
                post2.setCommunity(savedCommunityDto2);
                PostEntity savedPost2 = postService.save(postMapper.mapFrom(post2));

                PostDto post3 = new PostDto();
                post3.setCreatedAt(LocalDateTime.now());
                post3.setTitle("Elden Ring DLC Opinions");
                post3.setContent("What do you think of the new Elden Ring DLC?");
                post3.setCreator(savedUserDto3);
                post3.setCommunity(savedCommunityDto3);
                PostEntity savedPost3 = postService.save(postMapper.mapFrom(post3));

                PostDto post4 = new PostDto();
                post4.setCreatedAt(LocalDateTime.now());
                post4.setTitle("Must-Read Fantasy Novels");
                post4.setContent("What fantasy books would you recommend?");
                post4.setCreator(savedUserDto4);
                post4.setCommunity(savedCommunityDto4);
                PostEntity savedPost4 = postService.save(postMapper.mapFrom(post4));

                PostDto post5 = new PostDto();
                post5.setCreatedAt(LocalDateTime.now());
                post5.setTitle("Must-Read Epic Novels");
                post5.setContent("What Epic books would you recommend?");
                post5.setCreator(savedUserDto4);
                post5.setCommunity(savedCommunityDto4);
                postService.save(postMapper.mapFrom(post5));

                // -------- COMENTARIOS --------
                CommentDto comment1 = new CommentDto();
                comment1.setCreatedAt(LocalDateTime.now());
                comment1.setContent("I love the new pattern matching in Java 21.");
                comment1.setCreator(savedUserDto2.getId());
                comment1.setPost(savedPost1.getId());
                CommentEntity savedComment1 = commentService.save(commentMapper.mapFrom(comment1));

                CommentDto comment2 = new CommentDto();
                comment2.setCreatedAt(LocalDateTime.now());
                comment2.setContent("The visual effects in that movie blew me away!");
                comment2.setCreator(savedUserDto3.getId());
                comment2.setPost(savedPost2.getId());
                CommentEntity savedComment2 = commentService.save(commentMapper.mapFrom(comment2));

                CommentDto comment3 = new CommentDto();
                comment3.setCreatedAt(LocalDateTime.now());
                comment3.setContent("I think the DLC adds a lot of depth to the lore.");
                comment3.setCreator(savedUserDto4.getId());
                comment3.setPost(savedPost3.getId());
                CommentEntity savedComment3 = commentService.save(commentMapper.mapFrom(comment3));

                CommentDto comment4 = new CommentDto();
                comment4.setCreatedAt(LocalDateTime.now());
                comment4.setContent("You should definitely check out The Name of the Wind.");
                comment4.setCreator(savedUserDto1.getId());
                comment4.setPost(savedPost4.getId());
                CommentEntity savedComment4 = commentService.save(commentMapper.mapFrom(comment4));

                // -------- UPVOTES --------
                UpvoteDto upvote1 = new UpvoteDto();
                upvote1.setCreatedAt(LocalDateTime.now());
                upvote1.setCreator(savedUserDto1.getId());
                upvote1.setComment(savedComment1.getId());
                upvoteService.save(upvoteMapper.mapFrom(upvote1));

                UpvoteDto upvote2 = new UpvoteDto();
                upvote2.setCreatedAt(LocalDateTime.now());
                upvote2.setCreator(savedUserDto3.getId());
                upvote2.setComment(savedComment2.getId());
                upvoteService.save(upvoteMapper.mapFrom(upvote2));

                UpvoteDto upvote3 = new UpvoteDto();
                upvote3.setCreatedAt(LocalDateTime.now());
                upvote3.setCreator(savedUserDto2.getId());
                upvote3.setComment(savedComment3.getId());
                upvoteService.save(upvoteMapper.mapFrom(upvote3));

                UpvoteDto upvote4 = new UpvoteDto();
                upvote4.setCreatedAt(LocalDateTime.now());
                upvote4.setCreator(savedUserDto4.getId());
                upvote4.setComment(savedComment4.getId());
                upvoteService.save(upvoteMapper.mapFrom(upvote4));

                UpvoteDto upvote5 = new UpvoteDto();
                upvote5.setCreatedAt(LocalDateTime.now());
                upvote5.setCreator(savedUserDto4.getId());
                upvote5.setComment(savedPost2.getId());
                upvoteService.save(upvoteMapper.mapFrom(upvote5));
            }

            System.out.println("Datos iniciales cargados.");
        };
    }
}
