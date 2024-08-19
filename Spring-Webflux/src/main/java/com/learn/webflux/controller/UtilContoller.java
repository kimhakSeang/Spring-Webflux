package com.learn.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webflux.dto.ResponseDto;
import com.learn.webflux.exceptionHandler.UtilException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exception")
public class UtilContoller {
//
//	@GetMapping(value = "/get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public ResponseEntity<Flux<Integer>> getNormal(){
//		
////		 List<Integer> listValue = IntStream.range(1, 10)
////				.peek(x-> {
////					try {
////						Thread.sleep(500);
////					} catch (InterruptedException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				})
////				.peek(System.out::println)
////				.mapToObj(i -> i)
////				.toList()
////				;
//		
//		Flux<Integer> valFlux = Flux.range(1, 10)
//				.doOnNext(x -> {
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				})
////				.delayElements(Duration.ofSeconds(1))
//				.doOnNext(System.out::println)
//				;
//		 
//		 return ResponseEntity.ok(valFlux);
//	}
//	
	@GetMapping("/flux")
	public Flux<Object> getException(){
		
		
		Flux<Object> valFlux = 
			Flux.range(1, 10)
				.handle((num, sink) -> {
					if( num > 8) {
						sink.error(new RuntimeException("Number > 8"));
					}else {
						sink.next(new ResponseDto("001", "Next : "+num));
					}
				})
				.onErrorResume(e -> Flux.just(new RuntimeException("Error Flux")));
				;
		 
		 return valFlux;
	}
	@GetMapping("/mono-err")
	public Mono<ResponseDto> getMonoException(){
		return Mono.fromSupplier(() -> 1 )
				.handle((number, sink) ->{
					if(number < 10 || number > 20) {
						sink.error(new RuntimeException(""));
					}else {
						sink.next(new ResponseDto("001", "Number :"+number));
					}
				});
	}
}
