@RestController
class Hello {
	@GetMapping('/{name}')
	def sayHello(@PathVariable String name) {
		return 'Hello ' + name 
	}
}
