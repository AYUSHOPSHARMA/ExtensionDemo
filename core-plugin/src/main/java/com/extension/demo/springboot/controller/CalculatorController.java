package com.extension.demo.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.extension.demo.springboot.ActivatorCore;
import com.extension.demo.springboot.api.CalculatorService;
import com.extension.demo.springboot.extension.CoreExtension;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@GetMapping
	public List<String> getAllOperations() {
		List<String> operations = new ArrayList<String>();
		try {
			  Map<String, ? extends Object> extensions = CoreExtension.getAllExtension(CalculatorService.class);
			  Iterator<?> itr = extensions.entrySet().iterator();
				while(itr.hasNext()) {
					System.out.println("");
					Entry<String, CalculatorService> entry=(Entry<String, CalculatorService>) itr.next();
					CalculatorService service=entry.getValue();
					operations.add(service.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operations;
	}

	@GetMapping("/{operation}")
	public int execute(@PathVariable("operation") String operation, @RequestParam("n1") int n1,
			@RequestParam("n2") int n2) {
		/*BundleContext bundleContext = ActivatorCore.getBundleContext();
		try {
			Collection<ServiceReference<CalculatorService>> references = bundleContext
					.getServiceReferences(CalculatorService.class, null);
			for (ServiceReference<CalculatorService> reference : references) {
				CalculatorService calcService = bundleContext.getService(reference);
				if (calcService.name().equalsIgnoreCase(operation)) {
					return calcService.execute(n1, n2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Invalid Operation");*/
		return 0;
	}
}
