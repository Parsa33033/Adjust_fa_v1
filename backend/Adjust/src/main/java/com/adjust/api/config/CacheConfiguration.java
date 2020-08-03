package com.adjust.api.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.adjust.api.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.adjust.api.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.adjust.api.domain.User.class.getName());
            createCache(cm, com.adjust.api.domain.Authority.class.getName());
            createCache(cm, com.adjust.api.domain.User.class.getName() + ".authorities");
            createCache(cm, com.adjust.api.domain.AdjustTokens.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustPrice.class.getName());
            createCache(cm, com.adjust.api.domain.ShopingItem.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustShopingItem.class.getName());
            createCache(cm, com.adjust.api.domain.Cart.class.getName());
            createCache(cm, com.adjust.api.domain.Cart.class.getName() + ".items");
            createCache(cm, com.adjust.api.domain.Order.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustClient.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustClient.class.getName() + ".tutorials");
            createCache(cm, com.adjust.api.domain.AdjustClient.class.getName() + ".programs");
            createCache(cm, com.adjust.api.domain.Specialist.class.getName());
            createCache(cm, com.adjust.api.domain.Specialist.class.getName() + ".programs");
            createCache(cm, com.adjust.api.domain.AdjustProgram.class.getName());
            createCache(cm, com.adjust.api.domain.BodyComposition.class.getName());
            createCache(cm, com.adjust.api.domain.FitnessProgram.class.getName());
            createCache(cm, com.adjust.api.domain.FitnessProgram.class.getName() + ".workouts");
            createCache(cm, com.adjust.api.domain.Workout.class.getName());
            createCache(cm, com.adjust.api.domain.Workout.class.getName() + ".exercises");
            createCache(cm, com.adjust.api.domain.Exercise.class.getName());
            createCache(cm, com.adjust.api.domain.Move.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustMove.class.getName());
            createCache(cm, com.adjust.api.domain.NutritionProgram.class.getName());
            createCache(cm, com.adjust.api.domain.NutritionProgram.class.getName() + ".meals");
            createCache(cm, com.adjust.api.domain.Meal.class.getName());
            createCache(cm, com.adjust.api.domain.Meal.class.getName() + ".nutritions");
            createCache(cm, com.adjust.api.domain.Nutrition.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustNutrition.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustNutrition.class.getName() + ".foods");
            createCache(cm, com.adjust.api.domain.AdjustFood.class.getName());
            createCache(cm, com.adjust.api.domain.Conversation.class.getName());
            createCache(cm, com.adjust.api.domain.Conversation.class.getName() + ".messages");
            createCache(cm, com.adjust.api.domain.ChatMessage.class.getName());
            createCache(cm, com.adjust.api.domain.Tutorial.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustTutorial.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustTutorialVideo.class.getName());
            createCache(cm, com.adjust.api.domain.TutorialVideo.class.getName());
            createCache(cm, com.adjust.api.domain.AdjustProgram.class.getName() + ".bodyCompostions");
            createCache(cm, com.adjust.api.domain.AdjustProgram.class.getName() + ".programDevelopments");
            createCache(cm, com.adjust.api.domain.ProgramDevelopment.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
