package farm.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import farm.enums.CacheType;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfiguration extends CachingConfigurerSupport {

	@Bean
	public CacheManager ehCacheManager() {
		
		CacheConfiguration animalCache = new CacheConfiguration(); 
		animalCache.setName(CacheType.ANIMAL_CACHE);
		animalCache.setMemoryStoreEvictionPolicy("LRU");
		animalCache.setMaxEntriesLocalHeap(1000);
		animalCache.setTimeToLiveSeconds(60*10);
		
		CacheConfiguration userCache = new CacheConfiguration(); 
		userCache.setName(CacheType.USER_CACHE);
		userCache.setMemoryStoreEvictionPolicy("LRU");
		userCache.setMaxEntriesLocalHeap(10);
		userCache.setTimeToLiveSeconds(60*10);
		
		CacheConfiguration groupCache = new CacheConfiguration(); 
		groupCache.setName(CacheType.GROUP_CACHE);
		groupCache.setMemoryStoreEvictionPolicy("LRU");
		groupCache.setMaxEntriesLocalHeap(50);
		groupCache.setTimeToLiveSeconds(60*10);
		
		net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
		configuration.addCache(animalCache);
		configuration.addCache(userCache);
		configuration.addCache(groupCache);

		net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.newInstance(configuration);
		EhCacheCacheManager ehManager =  new EhCacheCacheManager(manager);
		ehManager.setTransactionAware(true);
		
		return ehManager;
	}
}
