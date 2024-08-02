package com.learn.reactive.service.util;

import java.util.function.UnaryOperator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Transformer {
	
	public static <T> UnaryOperator<Flux<T>> fluxTracking(String path){
		return flux -> flux.doFirst(() -> log.warn("Received: "+path))
				           .doOnCancel(()->log.error("Canceled: "+path))
				           .doOnComplete(() -> log.warn("Completed: "+path));
	}
	

}
