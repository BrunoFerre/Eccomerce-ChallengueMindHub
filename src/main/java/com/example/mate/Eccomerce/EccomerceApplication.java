package com.example.mate.Eccomerce;

import com.example.mate.Eccomerce.models.*;
import com.example.mate.Eccomerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class EccomerceApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EccomerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PersonRepository personRepository, AdressRepository adressRepository, ProductRepository productRepository) {
		return (args) -> {
			Person admin = new Person("Bruno Marcos", "Ferreira",
					"fbrunomarcos@gmail.com", passwordEncoder.encode("pass123"), PersonType.ADMIN);
			Adress adress = new Adress("Calle Admin", 123, "Jardin America", "Casa", 2, true, "CP1239");
			admin.addAdress(adress);
			personRepository.save(admin);
			adressRepository.save(adress);

			//Products
			//Mates

			Product product1 = new Product("MATE IMPERIAL WAXED CAROB", "The Imperial Algarrobo Wood Mate is a tribute to the Argentine mate tradition, elevated through noble materials and exceptional design. Each mate is hand carved from carob wood, highlighting its natural beauty and providing a rustic and authentic. The nickel silver strap adds a flash of elegance to the wood, enhancing the piece with its characteristic shine and durability. The steel ferrule on the edge of the mat adds a modern and durable touch, fusing the traditional with the contemporary which can be PERSONALIZED." +
					"-Height: 10 CM" +
					"Width of ferrule: 9CM", 72.55, 100, CategoryProduct.MATES, ColorProduct.BROWN, 0, "https://d22fxaf9t8d39k.cloudfront.net/404e695dd7458678204ef273e8a72cf559939c03f46c774f420cc348674adf1719762.jpg", List.of("https://d22fxaf9t8d39k.cloudfront.net/01b666741fb53d8767aa0b40aa69acda0fb420de30b6d05b3ba5d3911faf833a19762.jpg", "https://d22fxaf9t8d39k.cloudfront.net/5a2978b3911155a4444361416edd48faa6f888b4a294d4ae71064b3d81def2b819762.jpeg"));
			productRepository.save(product1);

			Product product2 = new Product("MATE IMPERIAL DELUXE PINK",
					"-Stainless steel thermal interior." +
							"-Steel Ferrule" +
							"-Alpaca Guard" +
							"-Does not require curing since it is made of steel." +
							"-Lined in cowhide." +
							"-Measurements: 11cm high and 7cm interior cavity, without taking into account the ferrule.\n" +
							"-Antifungals.", 109.94, 15, CategoryProduct.MATES, ColorProduct.PINK, 15.00,
					"https://d22fxaf9t8d39k.cloudfront.net/c733d98288a81023a6494f496378d9a04613e68b02caa75543291fe43af74f5819762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/09962541e320cd119c33cade4e388e878417e5bbabbe732118254477a88db89c19762.jpeg"));
			productRepository.save(product2);

			Product product3 = new Product("THERMAL STEEL MATT", "Stainless Steel Mate" +
					"- Extra-hygienic product." +
					"- Double layer of steel (makes it not hot in the hand)" +
					"- Stainless steel." +
					"- Capacity: 40 grams of herb or 160ml" +
					"- Measurements: 8.5cm height x 8cm diameter" +
					"- 100% handmade product." +
					"-Super practical" +
					"Stainless Steel Bulb",
					44.75,
					62,
					CategoryProduct.MATES,
					ColorProduct.BLACK,
					36.00,
					"https://d22fxaf9t8d39k.cloudfront.net/1afb26fb96198adb2ff6aa9e52681005198466758bf05544d2f3fb62f13fd5ab19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/21133a072d94429c9f2bb5d6f892c5cb1a9bca3655f7d48672acb8d2d5140e2719762.jpeg", "https://d22fxaf9t8d39k.cloudfront.net/ac3e45c43a722587822155e327a82a16e70e12a2533f76761a678c1ff120163b19762.jpeg", "https://d22fxaf9t8d39k.cloudfront.net/52e3f95ba9078767f1324acfe7655f3c6d66a7b6c0ba0649be25247b18ab75fc19762.jpeg"));
			productRepository.save(product3);

			Product product4= new Product("IMPERIAL DELUXE CROCO WHITE","Mate Imperial Deluxe Croco Blanco is a revolution in the mate experience, offering pure flavor and sophisticated design." +
					"Steel Interior: The steel cavity inside the mate guarantees the purity of the flavor." +
					"Steel Ferrule: The steel ferrule on the edge of the matte adds a modern and standout touch, ensuring an elegant appearance and long life." +
					"Alpaca Guard: The nickel silver guard surrounds the body of the mate, providing a distinctive shine and a luxurious feel." +
					"It does not require curing since it is made of steel." +
					"Lined in cowhide." +
					"Measurements: 11cm high and 7cm interior cavity, without taking into account the ferrule.",
					132.09,
					84,
					CategoryProduct.MATES,
					ColorProduct.WHITE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/8192e614edf53c65228611a92cfaca9253f682d613bbb169a36174f744cff9d019762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/36e35b28274cb04f135e9f01f0c6fcf73d7f8dac0eefc4ee0788567da605c6ce19762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/a40138f5f9d67212f38c7d3f9248c8a6413637ffb6f957b3369bc1cbdb4af37e19762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/b4f8e26d953b52e25b4764e3c2c52bc7e3444fe87e25fc5b1b095d0de1a21c1819762.jpeg"));
			productRepository.save(product4);

			Product product5=new Product("IMPERIAL DELUXE MATE","-Stainless steel thermal interior." +
					"-Steel ferrule" +
					"-Alpaca guard" +
					"-It does not require curing since it is made of steel." +
					"-Lined in cow leather." +
					"-Measurements: 11cm high and 7cm interior cavity, without taking into account the ferrule." +
					"-Antifungal.",
					98.13,
					26,
					CategoryProduct.MATES,
					ColorProduct.BLACK,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/db40dbff11da303aa976cd297f69bb89ecda14cc41edbf543cca6dd3f87fc33d19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/e6a9be125f761702b383f5f5efdce940be5625e858aae9fbc0c9b1cd6810d39a19762.jpeg"));
			productRepository.save(product5);

			Product product6= new Product("IMPERIAL PREMIUM MATE - ALPACA BASE",
					"Due to its delicate craftsmanship, the imperial mate became the desire of every Argentine mate." +
					"Each piece is unique, meticulous craftsmanship, distinguishing each imperial mate from the rest. Handcrafted work in alpaca and leather." +
					"CHARACTERISTICS:" +
					"Brazilian pumpkin mate" +
					"Lined in cowhide" +
					"Base with 4 reinforced legs." +
					"Chiseled nickel silver strap" +
					"Alpaca Base" +
					"PRODUCT OF EXCELLENCE" +
					"MADE IN URUGUAY" +
					"We only sell PREMIUM quality:" +
					"-Alpaca guard" +
					"-Beef leather covering the pumpkin" +
					"-Brazilian pumpkin." +
					"It is a mate that with the correct care lasts a lifetime.",
					112.07,
					17,
					CategoryProduct.MATES,
					ColorProduct.BLACK,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/49613ecffd197442fad3bb818d2e4949b995f555c15a513230a9fd98fc856a7719762.jpg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/a0e5b974013ec189cfa0d56a759565e8ca25697f6cd3f9c3ccbeaeca4e1cfaee19762.png"));
			productRepository.save(product6);

			Product product7=new Product("IMPERIAL PREMIUM MATE - ALPACA BASE",
					"Due to its delicate craftsmanship, the imperial mate became the desire of every Argentine mate." +
							"Each piece is unique, meticulous craftsmanship, distinguishing each imperial mate from the rest. Handcrafted work in alpaca and leather." +
							"CHARACTERISTICS:" +
							"Brazilian pumpkin mate" +
							"Lined in cowhide" +
							"Base with 4 reinforced legs." +
							"Chiseled nickel silver strap" +
							"Alpaca Base" +
							"PRODUCT OF EXCELLENCE" +
							"MADE IN URUGUAY" +
							"We only sell PREMIUM quality:" +
							"-Alpaca guard" +
							"-Beef leather covering the pumpkin" +
							"-Brazilian pumpkin." +
							"It is a mate that with the correct care lasts a lifetime.",
					112.07,
					27,
					CategoryProduct.MATES,
					ColorProduct.BROWN,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/2fe199a98d7972ef454dba2877f1a8c1c58e1efe9c3931283597558dec74123919762.jpg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/e2375c248ef6f500a4fdb1d835ae6f58145083e9665b333d6e93b8656267072919762.jpg"));
			productRepository.save(product7);

			Product product8=new Product("PREMIUM TORPEDO MATE + PARROT BEAK BULB",
					"Uruguayan mate torpedo with smooth steel ferrule, it is lined in leather and its interior is made of Brazilian gourd (heavier and more resistant)" +
							"Excellent Quality Leather: Each mate is carefully covered in high-quality leather, adding a touch of elegance and durability to your mate experience." +
							"Ergonomic Shape: The torpedo-shaped design not only fits comfortably in your hand, but also enhances its sophisticated appearance.",
					71.04,
					39,
					CategoryProduct.MATES,
					ColorProduct.BROWN,
					12.00,
					"https://d22fxaf9t8d39k.cloudfront.net/da693c24a140238ec92f548f6401d117f19430cf9c71b5d20fd62c36484aaf0a19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/64dab32a8b9beea79623f57a250b6d5a2af0de2b679067c82c6e20ab64b601e119762.jpg","https://d22fxaf9t8d39k.cloudfront.net/d9166634933cc18096a40c476d1612a50881ce767d2e03ceaaf16f0ac966f86619762.jpeg"));
			productRepository.save(product8);

			Product product9= new Product("ENAMEL CERAMIC MATE BLACK",
					"Characteristics: " +
							"-Matte glazed ceramic in 2 colors: black and white." +
							"-Paradise wood base turned, sanded and varnished to protect it." +
							"-from water and stains." +
							"-Size: 11 cm x 10 cm high." +
							"The herb on the ceramic maintains all its properties without absorption, which makes its flavor invariable.",
					81.06,
					54,
					CategoryProduct.MATES,
					ColorProduct.BLACK,
					50.00,
					"https://d22fxaf9t8d39k.cloudfront.net/6e471dc7d3900c2f80f25c013b4046c3119fe570c894749f789c2a90a65b026919762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/020984f8337788c76d416043a84c30797c9afd89c5fb788c0339507c9e3b54ae19762.jpeg"));
			productRepository.save(product9);

			Product product10= new Product("ENAMEL CERAMIC MATE WHITE",
					"Characteristics: " +
							"-Matte glazed ceramic in 2 colors: black and white." +
							"-Paradise wood base turned, sanded and varnished to protect it." +
							"-from water and stains." +
							"-Size: 11 cm x 10 cm high." +
							"The herb on the ceramic maintains all its properties without absorption, which makes its flavor invariable.",
					81.06,
					54,
					CategoryProduct.MATES,
					ColorProduct.WHITE,
					50.00,
					"https://d22fxaf9t8d39k.cloudfront.net/4789f30b6f87ed62f2457156b945c2274a4b055050771b2f3772662a8654acd819762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/787e53202b07329109955104e384e1a58e640846834753eaf79ee6e2316b270719762.jpeg"));
			productRepository.save(product10);

			//BULBS
			Product product11= new Product("PARROT BEAK LIGHT BULB - STEEL",
					"Bulb / Brazilian Alpaca Model Parrot beak" +
							" Brazilian alpaca has more bronze in its alloy, so it is lighter." +
							" 19 cm in extension, the most used today by the Matero public.",
					14.71,
					450,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/18b89e7248b473dd9093f234b047e7af6c768d41c5e0ab7ee25ef98eeadc30fa19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/d4c15de5dc3e2198e1907f3fbe28589d9a88a151061c45e920202960eefd793c19762.jpeg"));
			productRepository.save(product11);

			Product product12= new Product("SMOOTH GOLDEN PARROT-BEAK MATE STRAW",
					"Introducing the Smooth Golden Mate Straw with Parrot's Beak for mate lovers!" +
							" This elegant straw features a sleek, shiny gold finish on its tip, adding a touch of glamour to your mate drinking experience." +
							" Crafted with precision and attention to detail, its unique parrot's beak design ensures optimal infusion and filtration." +
							" With this straw, you can enjoy a seamless flow of mate without any unwanted particles." +
							" Elevate your mate ritual and indulge in a luxurious sip every time with the Smooth Golden Parrot's Beak Mate Straw.",
					59.17,
					28,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/52f803ec07d39749eda8d86b1fb69bc72ab54cb5672773f4e4aa23004d71db3219762.jpg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/4aec489271253b9a37c47dfa10e183364fe09604fd958b3be14c246f6337d45719762.jpg","https://d22fxaf9t8d39k.cloudfront.net/563b1b7c3387286afaf6412b85ffd96574d5d6b8936c0781d7609d5012c731a519762.png","https://d22fxaf9t8d39k.cloudfront.net/d13a10a41a069e920886b9b0b4cc0f09864f0ee59ec51fe944e55b993999343119762.jpg"));
			productRepository.save(product12);

			Product product13= new Product("SMOOTH KING'S PEAK BULB",
					"Introducing the Smooth King's Peak Mate Straw: the perfect companion for your mate drinking journey!" +
							" Crafted with great care, this straw features a smooth and elegant design that guarantees a delicious mate experience." +
							"With its exclusive King's Peak bulb shape, it ensures optimal filtration while enhancing the aroma and flavor of your mate. " +
							"The stainless steel construction ensures durability and easy cleaning." +
							" Elevate your mate drinking ritual with the Smooth King's Peak Mate Straw and enjoy a smooth, satisfying sip every time. Live mate like a true king!",
					48.81,
					120,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/7b961cc68f688cb472575ebd5b49f939f6323e177df78a6338901d0ef69de64e19762.png",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/d2bad2d43eff69455a23fbe86c497319a88220fbe31e3bc06322506b008739fc19762.png","https://d22fxaf9t8d39k.cloudfront.net/665f1e179a8d627cb93ee0411cf75a41eabb673591d14f2298a994049ea1921619762.png","https://d22fxaf9t8d39k.cloudfront.net/7b961cc68f688cb472575ebd5b49f939f6323e177df78a6338901d0ef69de64e19762.png","https://d22fxaf9t8d39k.cloudfront.net/72c548f0844d89f0e91afd23068366e5876b6d7ab30c83447871894ed5a89f0219762.png","https://d22fxaf9t8d39k.cloudfront.net/fafe4f5e1682aaafa5806158e0f6f62dbad4db6dcf37f940932a51423e8c8ef919762.png"));
			productRepository.save(product13);

			Product product14= new Product("COPPER PARROT-BEAK MATE STRAW",
					"Introducing the Copper Parrot-Beak Mate Straw : the epitome of elegance and functionality for mate enthusiasts!" +
							" This exquisite straw features a stunning copper finish that adds a touch of sophistication to your mate drinking experience." +
							" The unique parrot beak design ensures a perfect flow of mate, while the copper material helps maintain the temperature of your drink." +
							" Crafted with precision and attention to detail, this mate straw guarantees a smooth, rich flavor in every sip." +
							" Enhance your mate ritual with the Copper Parrot-Beak Mate Straw and enjoy the perfect infusion of mate." +
							" Release the true essence of mate with style!",
					61.78,
					62,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					24.00,
					"https://d22fxaf9t8d39k.cloudfront.net/41574823edef0d9fc560776d232296590eeeb3a14e16a472bda1990decdcf97119762.png",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/08514c55162f399fc0f6e3697f36758d56ec4707c51eb9181820906fc145340d19762.png","https://d22fxaf9t8d39k.cloudfront.net/7b3c58ed3a7cfc8ecb0fe8257f47bec0227512eb02e75ca27ea5c55c41635c5119762.png","https://d22fxaf9t8d39k.cloudfront.net/21c949f8141d44fadd491d9e5bb90d8d92d17092327428173c55faca4ae3361a19762.png"));
			productRepository.save(product14);

			Product product15= new Product("SEMI STRAIGHT BULB",
					"Introducing the Semi-Straight Bulb: the perfect companion for your mate drinking trip!" +
							" Crafted with precision, this bulb features a semi-straight design that offers a unique twist to traditional mate straws." +
							" The stainless steel construction ensures durability and easy cleaning, while the semi-straight shape allows for a comfortable and smooth drinking experience." +
							" Experience the perfect brew every time as the bulb filters out any unwanted particles, allowing you to savor the full flavor of your mate." +
							" Elevate your mate ritual with the Semi-Straight Bulb and enjoy the true essence of mate.",
					49.94,
					54,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					12.00,
					"https://d22fxaf9t8d39k.cloudfront.net/57f9c4e1661be617689607bc80b46e8c68d3b9611e4825689234e70de9ac558a19762.png",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/9ba5a52c5f42e0e0b867a07d498fd42b2d975a8295c2bebba45e9a81f642d38919762.png","https://d22fxaf9t8d39k.cloudfront.net/b343d32741afbcac39f71d9b2b8763472c5f667ad3222409991fd8a04db6eec519762.png","https://d22fxaf9t8d39k.cloudfront.net/026aa0a95524c822f710626b09db787bc6859dbfee47a97209a11712122f92e319762.png"));
			productRepository.save(product15);

			Product product16= new Product("KING'S PEAK BULB",
					"Introducing the Kings Peak Bulb Mate Straw: the perfect companion for a regal mate-drinking experience!" +
							" This exquisite straw features a bulb-shaped design inspired by the majestic Kings Peak." +
							" Crafted with precision and attention to detail, it ensures optimal filtration for a smooth and flavorful mate infusion." +
							" The stainless steel construction offers durability and easy cleaning." +
							" Elevate your mate ritual with the Kings Peak Bulb Mate Straw and enjoy the true essence of mate drinking fit for royalty." +
							" Reign over your mate sessions with elegance and sophistication.",
					59.19,
					61,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					31.00,
					"https://d22fxaf9t8d39k.cloudfront.net/547e838fee1358eef5a4a28a7f73f04126c8aef5cee46275e186f90e4e00d42c19762.png",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/1eb5050dfdd9365bf91aad071de2d49270a4bd7226c7da7486e6fef33404f0b019762.png","https://d22fxaf9t8d39k.cloudfront.net/e65b84890ec531adef0a6820ab88c06c88791de52fe53b67cbf6ea1f3d044ae119762.png","https://d22fxaf9t8d39k.cloudfront.net/76be2bf2483d99efed04c8374bbacde839a22b8479440431bd9b7da739e1c0f219762.png","https://d22fxaf9t8d39k.cloudfront.net/7d6a5f647d66026c05fdbb9fa324ca90e04d8f1135f34a404fe7534d7f9e35f819762.png"));
			productRepository.save(product16);

			Product product17= new Product("GOLDEN KING PEAK MATE STRAW",
					"Introducing Golden King's Peak Mate Straw - the epitome of luxury for mate enthusiasts!" +
							" This gorgeous mate straw features a dazzling gold finish that adds a touch of opulence to your mate drinking experience." +
							" With its exclusive King's Peak bulb tip design, it guarantees optimal filtration and infusion, enhancing the aroma and flavor of your mate." +
							" Crafted with meticulous attention to detail, the durable stainless steel construction ensures long-lasting use and effortless cleaning." +
							" Elevate your mate ritual to royal status with the Golden King's Peak Mate straw and enjoy a golden sip of mate fit for a king or queen." +
							" Embrace the majestic essence of mate with style and elegance.",
					59.19,
					31,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/4fa2dfeba8206fd2d985734efd128118865bd8248a960332f6e6f3e484eef44719762.png",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/f9a6e40dba52e0d95d8093402c0f09f6726f53cd2d3495f757d495e1cdd08e4619762.png","https://d22fxaf9t8d39k.cloudfront.net/22cad0b71570b642d6718e1f855da88edc0ed8065e82a3640fc83cc01eaadcec19762.png","https://d22fxaf9t8d39k.cloudfront.net/5589ae3abc9310803fbe5ae81fdc135ada67516f1aad6a90258b3730696882f719762.png","https://d22fxaf9t8d39k.cloudfront.net/a90784737aba0d3ef75ecad18e3e3e8ffea4700d6087f72b7a88439fb13e68b919762.png"));
			productRepository.save(product17);

			Product product18= new Product("CHISELED ALPACA MATE STRAW",
					"Introducing the Chiseled Alpaca Mate Straw: a perfect fusion of strength and style for mate lovers! " +
							"This exceptional mate straw showcases a chiseled design inspired by the raw beauty of the Andes. " +
							"Crafted with premium alpaca metal, it offers remarkable durability and a unique aesthetic appeal. " +
							"The intricate chiseled patterns add a touch of elegance to your mate-drinking experience. With its expert construction, this mate straw ensures optimal filtration, delivering a smooth and satisfying sip every time. " +
							"Elevate your mate ritual with the Chiseled Alpaca Mate Straw and embrace the Andean spirit while enjoying the true essence of mate." +
							" Embark on a journey of unparalleled mate enjoyment with this remarkable mate straw.",
					60.71,
					83,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					10.00,
					"https://d22fxaf9t8d39k.cloudfront.net/918a2b36150c254c706c683877d5e3e2efe03a9ff42611cf5e3fbc91355d629519762.jpeg",
					List.of());
			productRepository.save(product18);

			Product product19= new Product("KING'S BEAK ALPACA MATE STRAW",
						"Introducing the Mate Straw King's Beak Alpaca: the perfect combination of elegance and functionality for mate lovers. " +
							"This exceptional mate straw features an nickel silver design inspired by the beauty of the Andes. Made with high quality materials, it guarantees durability and a unique visual appearance. " +
							"The Pico de Rey design provides optimal filtration, allowing you to enjoy a smooth and satisfying mate in every sip. " +
							"Enhance your mate ritual with the Mate Straw King's Beak Alpaca and experience the true essence of mate while enjoying its elegant alpaca design. " +
							"Discover an unmatched mate experience with this extraordinary mate straw.",
					56.04,
					61,
					CategoryProduct.BULBS,
					ColorProduct.NONE,
					13.00,
					"https://d22fxaf9t8d39k.cloudfront.net/5f72ba7c5047f1aa312032ec281916feb47fe716997286536900bca5b5c4acfa19762.jpeg",
					List.of("https://i.postimg.cc/zGFhGTxG/e9cb64b1-0381-476e-b547-064aee110dfc.jpg","https://i.postimg.cc/KzyCDSnN/bombilla-1-transformed.jpg"));
			productRepository.save(product19);

			Product product20= new Product("THERMO MATEANDO HALF HANDLE",
					"HALF HANDLE THERMO 1 Lt" +
							"Imported" +
							"Stainless steel" +
							"Grip Handle" +
							"primer beak" +
							"Conservation 12-14 hours" +
							"Versatile and comfortable for everyday use" +
							"Excellent Price/Quality ratio",
					41.01,
					83,
					CategoryProduct.THERMOS,
					ColorProduct.SILVER,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/30c3726d7f1b572a094f5bb44116d676720ad23e8392896f9e376d0999c873ee19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/b111ec31175e3f6d7cd7c693f321a92af0808bf67e6ef6a256c0c00efad8a40019762.jpeg"));
			productRepository.save(product20);

			Product product21= new Product("TERMO TERMOLAR REVOLUTION 1LT ACERO INOXIDABLE",
					"MATE'S TRUE COMPANION." +
							"PREMIUM HALF HANDLE." +
							"FOR THE REAL MATERANS." +
							"CHARACTERISTICS" +
							"-Cold / Hot" +
							"-Capacity 1 Lts" +
							"-Up to 12 hours" +
							"-Stainless steel inside and out" +
							"-Does not have a glass bulb" +
							"-Double action pouring lid included",
					119.58,
					28,
					CategoryProduct.THERMOS,
					ColorProduct.SILVER,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/a9d8d98ba7ca81a454d1dc769da85ac56cd8c729d2bf412fa46507147264590919762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/9b832d881454f65586a2c64f0a74db6a1fc8eb9a7a20e7f22223f12efe4a347219762.webp","https://d22fxaf9t8d39k.cloudfront.net/27dd2b85d3792be0fe6c7aa51c816d7ad6702cb64f0c0cb4e2fbb38509c5ff4419762.webp"));
			productRepository.save(product21);

			Product product22= new Product("STANLEY MATE-SYSTEM THERMO 800ml",
					"Mate is a very special drink in Argentina, and to enhance your mate experience, Stanley has created the Stanley Classic 800 ml Mate Thermos, an ideal companion for mate." +
							"With exclusive technology, this thermos has a very high precision cap that allows water to be poured directly at the desired point for a perfect barley with continuous fluid." +
							"  The stainless steel lid converts into a mate so you can enjoy the drink anywhere. In addition, the Stanley Thermo Mate keeps your water at the ideal temperature so you can enjoy your hot mate for longer." +
							"Whether you enjoy it alone or in company, the Stanley Classic 800 ml Thermo Mate has the ability to transform your mate moment into something legendary.",
					153.68,
					43,
					CategoryProduct.THERMOS,
					ColorProduct.BLACK,
					0,
					"https://stanleypm.vtexassets.com/arquivos/ids/156717-800-auto?v=638237541944070000&width=800&height=auto&aspect=true",
					List.of("https://stanleypm.vtexassets.com/arquivos/ids/156718-800-auto?v=638237542076700000&width=800&height=auto&aspect=true","https://stanleypm.vtexassets.com/arquivos/ids/156719-800-auto?v=638237542234000000&width=800&height=auto&aspect=true"));
			productRepository.save(product22);

//			Product product23= new Product("CLASSIC STANLEY THERMO 950ml WITH HANDLE AND PRIMER CAP",
//					"This thermos is made for the toughest of the tough, for those who demand the best in durability and performance, QuadVac insulation (four layers), this is achieved by additionally covering the stainless steel walls with vacuum side layers." +
//							" Keeps your liquids hot for 40 hours." +
//							" No matter what you're doing or where you're going, this thermos is ready when you need it!",
//					145.14,
//					25,
//					CategoryProduct.THERMOS,
//					ColorProduct.GREEN,
//					18,
//					"https://stanleypm.vtexassets.com/arquivos/ids/156812-800-auto?v=638242706009430000&width=800&height=auto&aspect=true",
//					List.of("https://stanleypm.vtexassets.com/arquivos/ids/156813-800-auto?v=638242706270770000&width=800&height=auto&aspect=true","https://stanleypm.vtexassets.com/arquivos/ids/156814-800-auto?v=638242706606300000&width=800&height=auto&aspect=true"));
//			productRepository.save(product23);

			Product product24= new Product("STANLEY THERMO 1.4ML PINK WITH HANDLES AND PRIMER CAP",
					"The word that comes to mind is iconic: elegant design, robust construction and an indescribable attitude." +
							"Completely leak-proof and capable of keeping drinks hot for 40 hours, cold for 45 hours or iced for 6 days." +
							"With the Stanley thermos you never have to worry about your water getting cold or your drink getting hot." +
							"Thanks to its thick steel external wall, you will get thermal performance that will last you for years.",
					162.21,
					22,
					CategoryProduct.THERMOS,
					ColorProduct.PINK,
					0,
					"https://stanleypm.vtexassets.com/arquivos/ids/156899-800-auto?v=638246926846330000&width=800&height=auto&aspect=true",
					List.of("https://stanleypm.vtexassets.com/arquivos/ids/156900-800-auto?v=638246927102630000&width=800&height=auto&aspect=true","https://stanleypm.vtexassets.com/arquivos/ids/156901-800-auto?v=638246927291270000&width=800&height=auto&aspect=true"));
			productRepository.save(product24);

			Product product25= new Product("STANLEY MATE-SYSTEM THERMO 1.2 LTS LIMITED EDITION UAR",
					"Mate has no time or place. It was thinking about making your experience even more special that we created the ideal companion for mate: the Mate System thermos." +
							"With exclusive technology, we create the highest precision cap for the perfect barley, with continuous flow of water and in the right place." +
							"The cap has the ideal slope to pour the water directly at the point you want." +
							"The stainless steel lid becomes matte* and the Stanley thermos keeps your water at the ideal temperature. Alone or accompanied, with the Stanley Mate System you have the ability to transform your mate into a legendary moment.",
					180.57,
					10,
					CategoryProduct.THERMOS,
					ColorProduct.GOLD,
					0,
					"https://stanleypm.vtexassets.com/arquivos/ids/156937-800-auto?v=638258878994770000&width=800&height=auto&aspect=true",
					List.of("https://stanleypm.vtexassets.com/arquivos/ids/156939-800-auto?v=638258885017830000&width=800&height=auto&aspect=true"));
			productRepository.save(product25);

			Product product26= new Product("PREMIUM LEATHER BLACK YERBA CONTAINER",
					"The Yerba Container 100% Leather is much more than a container; It is a statement of style and quality in every aspect of your matte experience." +
							"100% Genuine Leather: The Yerba Container is made of high quality leather that not only adds a touch of elegance, but also ensures durability and resistance." +
							"Its exquisite design reflects its appreciation for tradition and good taste. The yerba container is more than a container; It's an accessory." +
							"Specifically designed to transport yerba, the yerba container maintains its freshness and quality with each transfer.",
					26.30,
					27,
					CategoryProduct.YERBA_STORAGE,
					ColorProduct.BLACK,
					25.00,
					"https://d22fxaf9t8d39k.cloudfront.net/89e234e77516a566bac3b69a24928bf02ef58f81740ae09c0bf288ab33cb60bd19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/ec08aa0403c81b8d95668231518cff191deef545c1479d32da5a5c024c70377519762.jpeg"));
			productRepository.save(product26);

			Product product27= new Product("PREMIUM LEATHER BROWN YERBA CONTAINER",
					"The Yerba Container 100% Leather is much more than a container; It is a statement of style and quality in every aspect of your matte experience." +
							"100% Genuine Leather: The Yerba Container is made of high quality leather that not only adds a touch of elegance, but also ensures durability and resistance." +
							"Its exquisite design reflects its appreciation for tradition and good taste. The yerba container is more than a container; It's an accessory." +
							"Specifically designed to transport yerba, the yerba container maintains its freshness and quality with each transfer.",
					26.30,
					18,
					CategoryProduct.YERBA_STORAGE,
					ColorProduct.BROWN,
					25.00,
					"https://d22fxaf9t8d39k.cloudfront.net/433ee3575698c769ff3ef6392cc7e54adb996dbaf3fe5a929e42ca1cb9c3631219762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/326554d926380b6494ff60ed464f24e56324bb76c695f7b038e70bfbcf807c6819762.jpeg"));
			productRepository.save(product27);

			Product product28= new Product("NEW IN! YERBA CONTAINER TAMBO",
					"DESCRIPTION:" +
							"- The Tambo Yerbero is made of top quality tin." +
							"- Suitable for contact with food." +
							"-It has a plastic pouring spout." +
							"-Capacity: 350 gr." +
							"-Dimensions:" +
							"-Height: 12cm" +
							"-Width: 9cm" +
							"-Without a doubt, an excellent accessory for your Mate.",
					20.35,
					21,
					CategoryProduct.YERBA_STORAGE,
					ColorProduct.BLACK,
					33.00,
					"https://d22fxaf9t8d39k.cloudfront.net/d6a9346a2f596eb0aa04201f50b8caf889a288c0ee5c4d8a2adda0b2246d70e819762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/15a8122583038fb0f12a281f87f1a0296925f545a90451c08df29ac2006d89f919762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/e53f75b5592193ea401d6b7254d72dabd33e28aaaccf3e88ad302dc867b756f519762.jpeg"));
			productRepository.save(product28);

			Product product29= new Product("HIGH QUALITY THICK LEATHER MATERA 4x4",
					"The High Quality Thick Leather Matera is an expression of luxury and practicality. Its intelligent design and premium leather make it an essential accessory for mate lovers." +
							"Space for Accessories: In addition to the mate and the thermos, the matera offers an extra compartment that can be adapted to store a yerbera, a bottle or any accessory you need." +
							"Excellent Quality Leather: Thick, durable leather not only adds a touch of elegance, but also ensures durability and resistance to everyday conditions.",
					88.91,
					24,
					CategoryProduct.MATERAS,
					ColorProduct.BLACK,
					0,
					"https://i.postimg.cc/PxXMmYXS/84360317-224d-4c42-bded-33968e13e768.jpg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/4bdd1326544d55f24ff485b2fd2db2fa79520b96aadb5ee4659f0fa210246bd219762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/e257420beb4a2b8c682558c2c79399927a2e70f60daf7a916d092173c8bbc6d519762.jpeg"));
			productRepository.save(product29);

			Product product30= new Product("HIGH QUALITY THICK LEATHER MATERA 4x4",
					"The High Quality Thick Leather Matera is an expression of luxury and practicality. Its intelligent design and premium leather make it an essential accessory for mate lovers." +
							"Space for Accessories: In addition to the mate and the thermos, the matera offers an extra compartment that can be adapted to store a yerbera, a bottle or any accessory you need." +
							"Excellent Quality Leather: Thick, durable leather not only adds a touch of elegance, but also ensures durability and resistance to everyday conditions.",
					88.91,
					24,
					CategoryProduct.MATERAS,
					ColorProduct.BROWN,
					0,
					"https://i.postimg.cc/6pzVBTLR/ff61678a-63e6-4394-b29e-0663ede0e0a7.jpg",
					List.of("https://i.postimg.cc/dt3w5xNY/b8298f56-b231-4c9b-bc0b-ca405a72896e.jpg"));
			productRepository.save(product30);

			Product product31= new Product("ALGARROBO SET: TRUCKER MATE + HALF HANDLE THERMO + ALPACA BULB",
					"Introducing the Algarrobo Set: Trucker Yerba Container + Insulated Flask with Handle + Alpaca Mate Straw. This exceptional set combines essential mate accessories for the ultimate mate experience. The Trucker Yerba Container, made from beautiful algarrobo wood, provides a stylish and practical way to store and access your yerba mate. The Insulated Flask with Handle keeps your mate hot for hours while the ergonomic handle ensures a comfortable grip. The Alpaca Mate Straw, crafted with precision and attention to detail, guarantees optimal filtration and a smooth mate flavor. Elevate your mate ritual with the Algarrobo Set and experience the perfect balance of functionality and elegance.",
					90.48,
					15,
					CategoryProduct.MATEROS_SET,
					ColorProduct.BROWN,
					13.00,
					"https://d22fxaf9t8d39k.cloudfront.net/3945f6308631a40c38fb6eccfa5333039a825f300b1cf8cbb5bce616ce4f145e19762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/2260ff9d610cbebbff221dac2a8e835ac6bba9d873f95f7f50ade006bf12c55619762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/5c5422e6c442fd772e8bdcf7c693ca22cbfd23de1f6bcf892855e5d09ac8e09619762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/2526e34eb8e2ba6d6ed5ee9499fb7da7f9dc00eec5df3c09bd93aa7ea96b858519762.jpeg"));
			productRepository.save(product31);

			Product product32= new Product("MATTE URUGUAYAN TRUCKER SET + HALF HANDLE THERMO + PICO PARROT BULB",
					"The product offered is a Uruguayan trucker mate set, which includes an ergonomic and durable gourd, with resistant stainless steel details that give it a modern touch and ensure its durability. The unique design enhances the natural beauty of the pumpkin, combining tradition and sophistication. Also included is a half-handle thermos with a 1-liter capacity, imported and made of stainless steel, with a grip handle, a primer spout and a temperature conservation of 12-14 hours. The set can also include a parrot beak bulb by default and a gift box, if desired.",
					108.02,
					12,
					CategoryProduct.MATEROS_SET,
					ColorProduct.BLACK,
					12.00,
					"https://d22fxaf9t8d39k.cloudfront.net/8bbad025024f800f3ca19e13c8666569ef8382a21809a0b58cd2a065118687ea19762.jpg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/f17350a0a41911c5d96b72236cfc9cf0653b2e838d611ab31b96d9f5138bf46019762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/3d5042350833a807759de896d74ed56957c250a8792a3d9ba01b927577d6702c19762.jpeg","https://d22fxaf9t8d39k.cloudfront.net/f1987eff31cf8074219d3159975c85d1ba58cdc34a6fd9f297c6a1f0b8d1c5aa19762.jpeg"));
			productRepository.save(product32);

			Product product33= new Product("ULTIMATE MATEROS SET",
					"For those who pay attention to every detail of a mate session, this mega combo includes everything you need. The set features our premium imperial mate gourd. It also includes a high-quality Alpaca bulb for a smooth and enjoyable sipping experience. The thermos with a 1 liter capacity and a comfortable handle keeps your mate warm for hours. The Tambo yerba container, weighing 330 grams, ensures that you will always have a fresh supply of yerba mate. And to carry it all, we've included the Mateando Outdoor backpack, designed specifically for mate lovers who love to explore the outdoors. Get ready to improve your couple's experience with the Ultimate Materos Set!",
					210.24,
					9,
					CategoryProduct.MATEROS_SET,
					ColorProduct.BLACK,
					15.00,
					"https://d22fxaf9t8d39k.cloudfront.net/0573daf1cccd984f3aee1648ba6e41110838b9b34cfca6c11ed71409aec1edb619762.jpeg",
					List.of());
			productRepository.save(product33);

			Product product34= new Product("MATERO PRO ELITE KIT",
					"This exclusive Matero Pro Elite Kit is perfect for those who obsess over every detail of their couple experience. It includes the elegant Mate Torpedo Uruguayan Premium in black, which combines tradition and sophistication in a unique design. The Pico parrot Alpaca bulb guarantees a smooth and pleasant sip. The stainless steel thermos with half handle, with a capacity of 1 liter, keeps your mate warm for hours. The Tambo yerba container, weighing 330 grams, guarantees a continuous supply of the best yerba mate. In addition, the Mateando Outdoor backpack has been specially designed for mate lovers who enjoy the outdoors. Get ready to take your experience with your partner to the next level with the Matero Pro Elite kit. Immerse yourself in the passion of mate with this exceptionally complete combo!",
					263.31,
					7,
					CategoryProduct.MATEROS_SET,
					ColorProduct.BLACK,
					25.00,
					"https://d22fxaf9t8d39k.cloudfront.net/1cf47720f0ad2f310a28dd1bc6af8646cc390fac078f003a3db6a31daf0cd08119762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/a3b2d9da09a6cd8b692c3af790392ab88db9a726f7bfcea06a868931ce09e5ef19762.jpeg"));
			productRepository.save(product34);

			Product	product35= new Product("STAINLESS STEEL MATE SET-BLACK",
					"The Stainless Steel Mate Set offers an extra-hygienic mate experience. The mate, made with double-layer stainless steel, ensures that it won't heat up in your hand. It is 100% handmade and has a capacity of 40 grams of yerba or 160ml. With dimensions of 8.5cm in height and 8cm in diameter, it provides a practical and compact size for on-the-go enjoyment. The stainless steel bulb provides a clean and durable sipping experience. Imported and made of high-quality stainless steel, the thermos with a medium handle ensures excellent heat preservation for 12-14 hours. It features a comfortable grip handle and a primer spout. Versatile and convenient for everyday use, it offers an excellent price-to-quality ratio. With a 1-liter capacity, this set is perfect for indulging in your mate rituals while enjoying the benefits of stainless steel durability and hygiene.",
					186.34,
					18,
					CategoryProduct.MATEROS_SET,
					ColorProduct.BLACK,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/de43837a20edfaf046ed0932527ec0ee21d2b2d2e0c0d2e56f0622fbe8f8c2f519762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/27202f9f5b51fcea66af96b6b4f1bfa56a503a797e672ebe12cffa4be720c13219762.jpeg"));
			productRepository.save(product35);

			Product product36= new Product("STAINLESS STEEL MATE SET-WHITE",
					"The Stainless Steel Mate Set offers an extra-hygienic mate experience. The mate, made with double-layer stainless steel, ensures that it won't heat up in your hand. It is 100% handmade and has a capacity of 40 grams of yerba or 160ml. With dimensions of 8.5cm in height and 8cm in diameter, it provides a practical and compact size for on-the-go enjoyment. The stainless steel bulb provides a clean and durable sipping experience. Imported and made of high-quality stainless steel, the thermos with a medium handle ensures excellent heat preservation for 12-14 hours. It features a comfortable grip handle and a primer spout. Versatile and convenient for everyday use, it offers an excellent price-to-quality ratio. With a 1-liter capacity, this set is perfect for indulging in your mate rituals while enjoying the benefits of stainless steel durability and hygiene.",
					186.34,
					18,
					CategoryProduct.MATEROS_SET,
					ColorProduct.WHITE,
					0,
					"https://d22fxaf9t8d39k.cloudfront.net/86978aff550d3d17292027bc0621c26c8b99ba53816ece21ff8bd004911cadc219762.jpeg",
					List.of("https://d22fxaf9t8d39k.cloudfront.net/d0c900621cd62016661f90e6cc7f5d09c7306cbe27213c17aa48a9f8a173210819762.jpeg"));
			productRepository.save(product36);

			Product product37= new Product("YERBA MATE ROSAMONTE SPECIAL SELECTION 1KG",
					"Experience the exceptional quality of Rosamonte Special Selection Yerba Mate. Carefully selected leaves are used prior to the production process, ensuring the highest level of taste and aroma. Made with the perfect balance of leaves and stems, this yerba mate provides a traditional and authentic flavor. The natural aging process of 12 to 24 months guarantees a smooth and rich taste. Gluten-free and available in  1kg presentation, this yerba mate is perfect for those seeking a premium and satisfying mate experience. Indulge in the unique flavors and benefits of Rosamonte Special Selection Yerba Mate.",
					7.97,
					63,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					0,
					"https://acdn.mitiendanube.com/stores/001/176/840/products/ym-rosamonte-seleccion-especial-1kg-001-8ebf2d37b3a9b8e74e16040879619751-1024-1024.webp",
					List.of("https://acdn.mitiendanube.com/stores/001/176/840/products/ym-rosamonte-seleccion-especial-1kg-021-3526678f6d7c702a4916040879621084-1024-1024.webp", "https://acdn.mitiendanube.com/stores/001/176/840/products/ym-rosamonte-seleccion-especial-1kg-011-74f1adea8fc8d64b4816040879621935-1024-1024.webp"));
			productRepository.save(product37);

			Product product38= new Product("TRADITIONAL YERBA MATE-CANARIAS",
					"Description: Experience the authentic flavors of Canarias Yerba Mate. Grown in the southern region of Brazil, the ideal climate allows mate leaves to develop their aromatic qualities. After harvesting, the leaves are carefully dried and aged for 4 to 6 months to ensure a consistently tasty experience. This finely ground yerba mate is prepared without stems, offering a traditional bitter infusion that aligns with the mate drinking culture in Uruguay. With its intense mate aromas and long-lasting preparation, Canarias Yerba Mate is the perfect choice for mate lovers. Available in a 1kg presentation, savor the rich flavor and immerse yourself in the world of Canarias Yerba Mate.",
					22.77,
					32,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					12.00,
					"https://www.gustoargentino.com/cdn/shop/products/Disenosintitulo_43_b1b6971c-9964-41dc-b217-0a28eb66e5b0.png?v=1678460465&width=640",
					List.of());
			productRepository.save(product38);

			Product product39= new Product("ITALIAN COFFEE MAKER 6 WELLS BLACK MAGEFESA",
					"Discover the outstanding Italian-style coffee maker Magefesa Colombia Noir! Made with high-quality aluminum, this elegant coffee maker is not only durable but also suitable for gas, electric, and vitroceramic stoves. Its handle and knob are made of heat-resistant bakelite, ensuring safety and comfort. With a capacity of approximately 350ml, it can brew up to 6 delicious cups of richly flavored coffee. Standing at a compact height of 19.5cm, it is convenient for any kitchen. Experience the authentic Italian coffee with Magefesa Colombia Noir! Trusted brand: Magefesa.",
					65.11,
					34,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					12.00,
					"https://acdn.mitiendanube.com/stores/102/583/products/colombia-noir-cafetera-italiana-magefesa-espana-espanola-aluminio-moka-pocillos-cafe-tienda-pepino-31-3afea726d8a870ccc516064233514296-1024-1024.webp",
					List.of("https://acdn.mitiendanube.com/stores/102/583/products/colombia-noir-cafetera-italiana-magefesa-espana-espanola-aluminio-moka-pocillos-cafe-tienda-pepino-11-04f669eecbfe9ecea516064233512197-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/colombia-noir-cafetera-italiana-magefesa-espana-espanola-aluminio-moka-pocillos-cafe-tienda-pepino-21-38fadeae9d27cbcab516064233513975-1024-1024.webp"));
			productRepository.save(product39);

			Product product40= new Product("PEUGEOT COFFEE GRINDER",
					" Crafted from solid wood with meticulous attention to detail, this coffee grinder is a must-have." +
							"-Brand: Peugeot" +
							"-Material: Wood" +
							"-Dimensions: 10 cm x 10 cm x 20 cm" +
							"-Burrs: Steel (The best burrs in the world)" +
							"-Features a container for freshly ground coffee.",
					418.26,
					10,
					CategoryProduct.COFFEE_GRINDER,
					ColorProduct.BROWN,
					0,
					"https://acdn.mitiendanube.com/stores/102/583/products/peugeot-molinillo-cafe-madera-manual-tienda-pepino-21-a49a198b45dc2b124214733718622198-1024-1024.webp",
					List.of("https://acdn.mitiendanube.com/stores/102/583/products/wooden_manual_coffee_grinder1-7931a5192e24b6f00e14733716821388-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bm8521-madera-pequeno-manual-de-la-mano-molino-de-grano-de-cafe-amoladora-molino-triturador-hogar-11f67395d00b9cd2b514733716947337-1024-1024.webp"));
			productRepository.save(product40);

			Product product41 = new Product("ELECTRIC BISTRO BODUM COFFEE GRINDER",
					"BODUM BISTRO ELECTRIC GRINDER" +
							"-Turns coffee beans from horrible to fine quickly and efficiently" +
							"-Compact and functional" +
							"-Stainless steel cutting blade, strong and durable." +
							"-Transparent lid allows visibility to achieve desired thickness." +
							"-The comfortable push-button allows operation by pulsation or continuous grinding." +
							"-150 WATTS" +
							"-The grinding is not regulated, you grind and press the button as desired." +
							"-MEASUREMENTS: Height 17 CM - Width 9.5 CM - Depth 8.7 CM" +
							"BRAND: BODUM",
					234.84,
					24,
					CategoryProduct.COFFEE_GRINDER,
					ColorProduct.BLACK,
					25,
					"https://acdn.mitiendanube.com/stores/102/583/products/bodum-bistro-molinillo-cafe-electrico-muela-grano-coffee-moledor-tienda-pepino-11-8cb9fc6e2b0b5a871515978770902859-1024-1024.webp",
					List.of("https://acdn.mitiendanube.com/stores/102/583/products/bodum-bistro-molinillo-cafe-electrico-muela-grano-coffee-moledor-tienda-pepino-71-22d0a298a7af8b76a515978770903154-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bodum-bistro-molinillo-cafe-electrico-muela-grano-coffee-moledor-tienda-pepino-31-5aa20bc6ee0d3a486f15978770904612-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bodum-bistro-molinillo-cafe-electrico-muela-grano-coffee-moledor-tienda-pepino-41-967d144719f4dad96f15978770905294-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bodum-bistro-molinillo-cafe-electrico-muela-grano-coffee-moledor-tienda-pepino-51-9ded23f861e6a86a0b15978770905697-1024-1024.webp"));
			productRepository.save(product41);

			Product product42 = new Product("BIALETTI MOKA EXPRESS 12-CUP",
					"The one and only original since 1933. It embodies the charm of Italian coffee tradition. This is the number one coffee maker in the world, with over 250 million units sold. Indulge in the rich aroma and exquisite taste of authentic Italian espresso with the Bialetti Moka Express. Crafted with precision and quality, this iconic coffee maker has been enchanting coffee lovers for decades. Experience the timeless tradition and unparalleled quality that has made the Bialetti Moka Express a true legend in the coffee world.",
					316.41,
					23,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.SILVER,
					0,
					"https://www.lafazenda.com.ar/uploads/productos/bialetti_12_moka_express.webp",
					List.of("https://acdn.mitiendanube.com/stores/102/583/products/bialetti-cafetera-italiana-moka-express-aluminio-9-pocillos-tienda-pepino-21-b565290c1115c7989815999286905431-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bialetti-cafetera-italiana-moka-express-aluminio-9-pocillos-tienda-pepino-61-3fa5c31f7214e8a04b16630171187742-1024-1024.webp","https://acdn.mitiendanube.com/stores/102/583/products/bialetti-cafetera-italiana-moka-express-aluminio-9-pocillos-tienda-pepino-51-80fbe58258ad47087916630171187288-1024-1024.webp"));
			productRepository.save(product42);

			Product product43 = new Product("BIALETTI RAINBOW YELLOW 6-CUP",
					"The Bialetti Rainbow Yellow 6-Cup Moka Pot produces a rich and authentic espresso in just a few minutes. With its eye-catching yellow design, Bialetti's aluminum pot features the iconic octagonal shape that evenly distributes heat to enhance the aroma and flavor of your coffee. This coffee maker combines style and functionality, providing a unique experience when brewing your favorite cup of coffee. Add a splash of color to your morning routine with the Bialetti Rainbow Yellow, the perfect choice for coffee enthusiasts.",
					181.70,
					15,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.YELLOW,
					16.00,
					"https://http2.mlstatic.com/D_NQ_NP_897440-MLA41362517701_042020-O.webp",
					List.of("https://http2.mlstatic.com/D_NQ_NP_761799-MLA41362517703_042020-O.webp"));
			productRepository.save(product43);

			Product product44 = new Product("BIALETTI SMART BLACK FRENCH PRESS",
					"The Bialetti Smart Black French Press is perfect for preparing delicious coffee using the immersion method, which preserves the essential oils that paper filters absorb." +
							"This coffee maker features a unique design with clean lines, a heat-resistant glass carafe customized with the Bialetti logo, a lid, plastic handle, and a silicone base." +
							"Bialetti is an Italian brand that has been making its mark since 1933. They were the ones who created the iconic Moka coffee maker, aiming to bring the café-style espresso experience to homes. Now, Bialetti surprises us with their Smart French Press, offering a different way to savor coffee." +
							"Enhance your coffee experience with the Bialetti Smart Black French Press, designed to deliver rich and flavorful coffee with ease.",
					111.90,
					17,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					0,
					"https://ruffocoffee.com/wp-content/uploads/2022/08/Fotos-Producto-pWeb-48.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2022/08/Fotos-Producto-pWeb-48.png","https://ruffocoffee.com/wp-content/uploads/2022/08/Fotos-Producto-pWeb-49.png","https://ruffocoffee.com/wp-content/uploads/2022/08/Fotos-Producto-pWeb-53.png"));
			productRepository.save(product44);

			Product product45 = new Product("BIALETTI SMART RED FRENCH PRESS",
					"The Bialetti Smart Red French Press is perfect for preparing delicious coffee using the immersion method, which preserves the essential oils that paper filters absorb." +
							"This coffee maker features a unique design with clean lines, a heat-resistant glass carafe customized with the Bialetti logo, a lid, plastic handle, and a silicone base." +
							"Bialetti is an Italian brand that has been making its mark since 1933. They were the ones who created the iconic Moka coffee maker, aiming to bring the café-style espresso experience to homes. Now, Bialetti surprises us with their Smart French Press, offering a different way to savor coffee." +
							"Enhance your coffee experience with the Bialetti Smart Red French Press, designed to deliver rich and flavorful coffee with ease.",
					111.90,
					17,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.RED,
					0,
					"https://ruffocoffee.com/wp-content/uploads/2022/07/5-4.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2022/07/4.png","https://ruffocoffee.com/wp-content/uploads/2022/07/Fotos-Producto-pWeb-9.png","https://ruffocoffee.com/wp-content/uploads/2022/07/6-2.png"));
			productRepository.save(product45);

			Product product46 = new Product("BODUM JAVA BLACK FRENCH PRESS 3-CUP",
					"Bodum, in its iconic plunger preparation method, brings us its Java model, distinguished by connoisseurs of good coffee around the world. This coffee maker offers us simplicity in preparation and purity in flavor." +
							"The Bodum Java has all the qualities to transform your day full of aromas by making a delicious coffee in a few minutes, with coarsely ground coffee and hot water." +
							"The Bodum Java coffee maker with its elegant-looking retro-style design, following the brand's motto, “a good design does not have to be expensive”, its simplicity, attractive and fast, has a borosilicate glass jug that does not alter the flavor of the coffee beans, achieving an ideal extraction of the aroma and flavor of the infusion. Its body, handle and lid are made of lightweight, BPA (Bisphenol) free plastic." +
							"Bodum is a Danish brand founded in Copenhagen in 1944 by Peter Bodum. Since 1974, it has been making an impact since they launched the first French press coffee maker.",
					111.71,
					26,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					23.00,
					"https://ruffocoffee.com/wp-content/uploads/2021/12/bodum-prensa-java-3poc-negra.jpg",
					List.of("https://ruffocoffee.com/wp-content/uploads/2021/12/bodum-prensa-java-3poc-negra.jpg"));
			productRepository.save(product46);

			Product product47 = new Product("MULTI-BEVERAGE DOLCE GUSTO PICCOLO XS BLACK COFFEE MAKER",
					"MULTI-BEVERAGE DOLCE GUSTO PICCOLO XS BLACK MOULINEX PV1A0858: Minimalist Design and Advanced Technology Manual Machine" +
							"Enjoy over 20 varieties of hot and cold beverages with the Dolce Gusto Piccolo XS Black. With its high-pressure system of up to 15 bars, it delivers professional-quality drinks with a silky layer of cream. This sleek black coffee maker has a 1L capacity, 1500W power, and features an anti-drip device and water level indicator. With manual dosing, you can customize your drink to perfection. Experience the convenience and style of the Dolce Gusto Piccolo XS Black.",
					279.39,
					17,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					10.00,
					"https://moulinexar.vtexassets.com/arquivos/ids/155801-800-800?v=637985929469570000&width=800&height=800&aspect=true",
					List.of("https://moulinexar.vtexassets.com/arquivos/ids/155838-800-800?v=637985930738870000&width=800&height=800&aspect=true","https://moulinexar.vtexassets.com/arquivos/ids/155874-800-800?v=637985935740200000&width=800&height=800&aspect=true"));
			productRepository.save(product47);

			Product product48 = new Product("MULTI-BEVERAGE DOLCE GUSTO GENIO S PLUS BLACK COFFEE MAKER",
					"MULTI-BEVERAGE DOLCE GUSTO GENIO S PLUS BLACK MOULINEX PV340858: Automated Machine with Minimalist Design and Advanced Technology" +
							"Experience the convenience and sophistication of the Multi-Beverage Dolce Gusto Genio S Plus. This sleek black coffee maker offers a boost function for intensified coffee flavor and a temperature selector for customization. With over 20 varieties of hot and cold beverages to choose from, the high-pressure system delivers professional-quality drinks with a smooth layer of crema. Enjoy the convenience of the descaling alert and energy-saving ECO mode. With a power of 1500W and a capacity of 0.8L, this machine is the perfect addition to your kitchen.",
					413.67,
					7,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					24.00,
					"https://moulinexar.vtexassets.com/arquivos/ids/155827-800-800?v=637985929734800000&width=800&height=800&aspect=true",
					List.of("https://moulinexar.vtexassets.com/arquivos/ids/155863-800-800?v=637985931028730000&width=800&height=800&aspect=true","https://moulinexar.vtexassets.com/arquivos/ids/155898-800-800?v=637985936001000000&width=800&height=800&aspect=true","https://moulinexar.vtexassets.com/arquivos/ids/155929-800-800?v=637985936342730000&width=800&height=800&aspect=true"));
			productRepository.save(product48);

			Product product49 = new Product("BLACK CAFECITY COFFEE MAKER",
					"BLACK CAFECITY COFFEE MAKER MOULINEX FG320558: 650W Power:" +
							"-Permanent metal filter for easy cleaning." +
							" -Heat-resistant glass carafe with a 6-cup capacity." +
							"- Anti-drip system allows for serving a cup of coffee before the brewing cycle is complete." +
							"- Removable filter." +
							"- Water level indicator." +
							"- 1.7L capacity." +
							"- Black color." +
							"The Cafecity Black Coffee Maker by Moulinex FG320558 is the perfect addition to your kitchen. With its 650W power, it brews your favorite coffee quickly and efficiently. The permanent metal filter ensures easy cleaning and eliminates the need for disposable filters. The heat-resistant glass carafe can hold up to 6 cups of coffee, allowing you to serve multiple guests or enjoy multiple cups throughout the day. The anti-drip system ensures a mess-free brewing process, so you can pour a cup of coffee before the cycle is complete. The removable filter and water level indicator make it easy to use and maintain. With a generous 1.7L capacity, you can brew enough coffee for the whole family. Embrace the simplicity and elegance of the Cafecity Black Coffee Maker.",
					75.97,
					26,
					CategoryProduct.COFFEE_MAKER,
					ColorProduct.BLACK,
					0,
					"https://moulinexar.vtexassets.com/arquivos/ids/155798-800-800?v=637985929441800000&width=800&height=800&aspect=true",
					List.of("https://moulinexar.vtexassets.com/arquivos/ids/155835-800-800?v=637985930705770000&width=800&height=800&aspect=true"));
			productRepository.save(product49);

			Product product50 = new Product("ETHIOPIA SIDAMA COFFEE - RUFFO COFFEE ROASTER",
					"SPECIALTY ETHIOPIA SIDAMA COFFEE - ROASTED IN OUR ROASTERY. -100% Arabica Beans." +
							"-Medium Roast, No Sugar Added." +
							"-250g." +
							"Experience the exquisite flavors of our specialty coffee, meticulously roasted in-house. Made from 100% Arabica beans, this coffee provides a delightful and well-balanced taste." +
							"The Ethiopia Sidama coffee originates in the Sidama region of Ethiopia. Grown at altitudes ranging from 1700 to 1900 meters, this coffee showcases the unique characteristics of the Heirloom variety. The natural processing method enhances the flavors and produces a moderate acidity." +
							"Indulge in the enchanting notes of red fruits, flowers, and caramel, complemented by a moderate body and lingering finish. With a remarkable score of 86, this coffee is a true testament to our commitment to quality." +
							"Enjoy the richness and depth of our specialty Ethiopia Sidama coffee, perfect for coffee enthusiasts seeking a unique and memorable experience.",
					16.86,
					140,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://ruffocoffee.com/wp-content/uploads/2023/06/Mas-fotos-productos-web-23-1.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2023/06/Mas-fotos-productos-web-24-1.png"));
			productRepository.save(product50);

			Product product51= new Product("BRASIL BOURBON COFFEE - RUFFO COFFEE ROASTER",
					"SPECIALTY BRASIL BOURBON COFFEE - RUFFO COFFEE ROASTER" +
							"Indulge in the exceptional flavors of our specialty coffee, expertly roasted in-house. Made from 100% Arabica beans, this coffee guarantees a delicious and premium experience." +
							"With a medium roast and no added sugar, each sip of this coffee delivers a delightful balance of flavors. Our 250g pack ensures you have enough to savor each moment." +
							"This Brasil Bourbon coffee hails from the Minas Gerais region in Brazil. Grown at an altitude of 1150 meters, the Bourbon variety showcases its unique character. The natural processing method enhances the juiciness and brings out distinct flavor profiles.\n" +
							"Embark on a sensory journey with notes of bittersweet cocoa and almonds. The silky body of this coffee resembles that of a peach." +
							"With a score of 82, this coffee exemplifies our commitment to quality. Explore the rich flavors and enjoy a truly remarkable coffee experience with our specialty Brasil Bourbon coffee.",
					12.86,
					154,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					12.00,
					"https://ruffocoffee.com/wp-content/uploads/2023/07/Mas-fotos-productos-web-27.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2023/07/Mas-fotos-productos-web-28.png"));
			productRepository.save(product51);

			Product product52 = new Product("INDIA MALABAR COFFEE - KARELA - RUFFO COFFEE ROASTER",
					"SPECIALTY INDIA MALABAR COFFEE - KARELA - RUFFO COFFEE ROASTER\n" +
							"Experience the richness of our specialty coffee, meticulously roasted in our own roastery. Made from 100% Arabica beans, this coffee guarantees an exceptional and refined taste." +
							"With a medium roast and no added sugar, every cup of this coffee offers a perfect balance of flavors. Our 250g package ensures you have enough to enjoy every sip." +
							"This India Malabar - Karela coffee originates from the Malabar - Karela region in India. Grown at an altitude of 1400 meters, this coffee boasts a unique blend of the Kent and Lempira varieties. The washing process, along with drying through the monsoon winds, creates a distinct low acidity profile." +
							"Indulge in the delightful notes of brown sugar, dried herbs, cedar, and dark tobacco. This coffee has a well-rounded body, providing a truly satisfying experience." +
							"With a remarkable score of 87, this coffee exemplifies our dedication to quality. Explore the captivating flavors and savor the exceptional nature of our specialty India Malabar - Karela coffee.",
					19.43,
					122,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://ruffocoffee.com/wp-content/uploads/2023/07/Mas-fotos-productos-web-25.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2023/07/Mas-fotos-productos-web-26.png"));
			productRepository.save(product52);

			Product product53 = new Product("COLOMBIA HUILA COFFEE - RUFFO COFFEE ROASTER",
					"COLOMBIA HUILA COFFEE - RUFFO COFFEE ROASTER" +
							"Introducing our Specialty Colombia Huila Coffee, expertly roasted in our own roastery. Made from 100% Arabica beans, this exquisite coffee promises a truly remarkable experience." +
							"With a medium roast and no added sugar, each sip of this coffee envelops your senses in a delightful balance of flavors. Our 250g pack ensures you can savor every moment." +
							"This coffee hails from the picturesque Huila region in Colombia, where it is cultivated at an altitude of 1500 meters. The Caturra and Castillo varieties shine through, enhanced by the meticulous washing process, which results in a low acidity profile." +
							"Indulge in the captivating notes of chocolate, honey, sweet orange, and nuts, creating a harmonious combination of flavors. With a score of 84 according to the Specialty Coffee Association (SCA), this coffee is a testament to its exceptional quality." +
							"Experience the essence of Colombia with our Specialty Colombia Huila Coffee, delivering a truly exceptional taste that will transport you to the coffee-growing regions of Huila.",
					15.14,
					164,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://ruffocoffee.com/wp-content/uploads/2023/04/Mas-fotos-productos-web-2023-04-11T155909.230.png",
					List.of("https://ruffocoffee.com/wp-content/uploads/2023/04/Mas-fotos-productos-web-2023-04-11T160156.515.png"));
			productRepository.save(product53);

			Product product54 = new Product("MILD YERBA MATE WITH STEM 500g - UNIÓN",
					"UNIÓN SUAVE ORIGINAL YERBA MATE is a mild yerba mate, carefully crafted with stems. It is characterized by a lighter flavor compared to regular yerba mate." +
							"The UNIÓN brand was specifically created for consumers who prefer a less intense bitterness in their yerba mate. The brand utilizes varieties of yerba that are milder in terms of mateine content and bitterness." +
							"Thanks to their careful selection of specific plants, UNIÓN has successfully transformed mate into a more accessible beverage for delicate palates." +
							"This particular Yerba Mate is particularly popular among new mate consumers, providing a gentle introduction to the tradition and taste of mate. " +
							"Experience the smoothness of UNIÓN SUAVE ORIGINAL YERBA MATE and embark on a delightful journey into the world of mate, perfect for those who prefer a mild and light flavor.",
					6.19,
					231,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					14.00,
					"https://www.gustoargentino.com/cdn/shop/files/Yerba-Mate-Suave-con-palo-500g-Union.png?v=1682697376&width=640",
					List.of());
			productRepository.save(product54);

			Product product55 = new Product("YERBA MATE ORIGINAL CON PALO - TARAGÜI",
					"ENJOY THE AUTHENTIC FLAVOR OF ARGENTINA WITH TARAGÜI ORIGINAL YERBA MATE WITH STEMS - 1kg or 500g." +
							"This high-quality yerba mate is harvested in the Corrientes region and is renowned for its balanced flavor, with a touch of bitterness that is typical of the best Argentine yerbas." +
							"With its fresh aroma and fine grinding, Taragüi yerba mate is perfect for enjoying at any time of the day when you need that calm boost of energy that a good mate provides." +
							"Take advantage of this yerba mate to share with friends and family and experience the authentic flavor of Argentine yerba mate.",
					3.67,
					122,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					0,
					"https://www.gustoargentino.com/cdn/shop/products/yerba-mate-Taragui-original-con-palo-500g.png?v=1680535250&width=640",
					List.of("https://www.gustoargentino.com/cdn/shop/files/Yerba-Mate-taragui.png?v=1688416681&width=640","https://www.gustoargentino.com/cdn/shop/files/Yerba-Mate-taragui-1.png?v=1688416680&width=640"));
			productRepository.save(product55);

			Product product56 = new Product("YERBA MATE WITH STEM - MAÑANITA",
					"LIGHT AND ACCESSIBLE, YERBA MATE MAÑANITA is ideal for those who want to enjoy a smooth and authentic Yerba Mate." +
							"Cultivated in the provinces of Misiones and Corrientes, in northern Argentina, YERBA MATE MAÑANITA is prepared using natural processes." +
							"The leaves are harvested at the right moment to limit the caffeine content and bitterness." +
							"Drying at high temperature for a short time allows the leaves to capture the aromas." +
							"The Yerba Mate is aged for several months in 30 kg bags to ensure a consistent and similar flavor from one harvest to another.",
					6.00,
					142,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					16.00,
					"https://www.gustoargentino.com/cdn/shop/products/yerba-mate-mananita-1kg.png?v=1680723110&width=640",
					List.of());
			productRepository.save(product56);

			Product product57 = new Product("YERBA MATE SILUETA NARANJA 500G - CBSÉ",
					"CBSÉ ORANGE SILHOUETTE YERBA MATE 500G " +
							"The CBSÉ Orange Silhouette Yerba Mate is a yerba mate with a orange flavor and less bitterness than a classic yerba mate." +
							"CBSÉ Orange Silhouette Yerba Mate is especially suitable for people trying to lose weight. The composition was worked on in this way to help eliminate fats." +
							"This exquisite blend of yerba mate with rose hip, pennyroyal, fennel, mint, lemon verbena, and boldo, flavored with orange. It is also enriched with vitamins B1, B2, B5, B6, B9, B12, and Zinc." +
							"This is how CBSÉ Orange Silhouette Yerba Mate 500g has an authentic and natural flavor that will perfectly adapt to your mate preparations.",
					8.5,
					85,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					9.7,
					"https://www.gustoargentino.com/cdn/shop/files/Yerba-Mate-Silueta-Naranja-500g-Cbse.png?v=1682613819&width=640",
					List.of());
			productRepository.save(product57);

			Product product58 = new Product("YERBA MATE AZUL TRADICIONAL - NOBLEZA GAUCHA",
					"Enjoy the authentic flavor of Nobleza Gaucha's Yerba Mate Azul Tradicional." +
							"With this mate, you have plenty to prepare traditional mate or tereré and share with friends and family. This high-quality yerba mate will make your mate experience unique.",
					6.2,
					112,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					7.00,
					"https://www.gustoargentino.com/cdn/shop/products/Yerba-Mate-Azul-Tradicional-500gr-Nobleza-Gaucha.png?v=1682694209&width=840",
					List.of());
			productRepository.save(product58);

			Product product59 = new Product("YERBA MATE SILUETE PLUS 500G - CACHAMATE",
					"Cachamate Siluet Plus is a unique blend of yerba mate and seaweed. This combination allows you to achieve a satiating sensation while helping to burn calories and fats. Additionally, the presence of green tea and orange peel in this blend provides you with antioxidants that will energize your body. Cachamate Siluet Plus is a well-balanced beverage that not only provides vitality but also promotes overall well-being. Incorporate this drink into your daily routine and enjoy the benefits of this harmonious fusion of ingredients.",
					7.57,
					65,
					CategoryProduct.YERBA_MATE,
					ColorProduct.NONE,
					0,
					"https://www.gustoargentino.com/cdn/shop/files/cachamate-yerba-mate-siluet-plus-500g-1.jpg?v=1690296475&width=640",
					List.of());
			productRepository.save(product59);

			Product product60 = new Product("COLOMBIA SUPREMO X 25 EA",
					"COLOMBIA SUPREMO X 25 U." +
							"This high-altitude coffee distinguishes itself with its large and attractive bean, symbolizing the refinement of Colombian coffee. The combination of selective harvesting and processing through careful beneficiation, washing, and drying methods results in an outstanding coffee. It is smooth, with a clean cup, relatively high acidity, a well-balanced body, pronounced aroma, and an excellent sensory profile." +
							"Content: 25 capsules." +
							"Compatible with Nespresso machines." +
							"Each capsule yields approximately 60 ml.",
					13.14,
					122,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					8.00,
					"https://www.lafazenda.com.ar/uploads/productos/capsulas_nuevas_colombia_x_25.webp",
					List.of("https://www.lafazenda.com.ar/uploads/productos/etiqueta_colombia_x_25.jpg","https://www.lafazenda.com.ar/uploads/productos/Reverso_colombia2.jpg"));
			productRepository.save(product60);

			Product product61 = new Product("COLOMBIA SUPREMO CAN x 50 UNITS",
					"COLOMBIA SUPREMO CANNED COFFEE x 50 UNITS" +
							"THIS HIGH-GROWN COFFEE is distinguished by its large and attractive beans and is the symbol of Colombian coffee refinement. The processes of selective harvesting and transformation of the fruit through its careful processing, washing, and drying together lead to the production of an outstanding coffee. It is smooth, with a clean cup, relatively high acidity, balanced body, pronounced aroma, and an excellent quality sensory profile." +
							"Contents: 50 capsules" +
							"Compatible with Nespresso machines" +
							"Each capsule yields approximately 60 ml" +
							"Intensity: 9/10" +
							"Feel free to let me know if there's anything else I can assist you with!",
					29.43,
					85,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					12.00,
					"https://www.lafazenda.com.ar/uploads/productos/colombia_x_50_lata.webp",
					List.of("https://www.lafazenda.com.ar/uploads/productos/Colombia_lata_2.webp","https://www.lafazenda.com.ar/uploads/productos/Colombia_lata_3.webp"));
			productRepository.save(product61);

			Product product62 = new Product("BRAZIL AGATHA X 25 EA.",
					"BRASIL AGATHA x 25 EA." +
							"ORIGINATING from the southernmost part of Minas Gerais, this coffee has a slightly sweet, smooth, and aromatic taste, with a long aftertaste. Its intense fragrance and full body, combined with slightly citrusy notes and fruity aromas, prolong the pleasure with a very low caffeine content."+
							"Content: 25 capsules" +
							"Compatible with Nespresso machines" +
							"Each capsule yields approximately 60 ml." +
							"INTENSITY: 7/10",
					13.14,
					54,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://www.lafazenda.com.ar/uploads/productos/capsulas_nuevas_brasil_x_25.webp",
					List.of("https://www.lafazenda.com.ar/uploads/productos/etiqueta_brasil_x_25.jpg","https://www.lafazenda.com.ar/uploads/productos/Reverso_brasil4.jpg"));
			productRepository.save(product62);

			Product product63 = new Product("HAZELNUT FLAVOR x 10 EA.",
					"Compatible with Nespresso machines, the Hazelnut flavor x 10 capsules offer a unique and delightful coffee experience. Each capsule delivers approximately 60ml of exquisite hazelnut-infused coffee, providing a balanced intensity of 7/10. Whether you want to start your day with a flavorful hazelnut coffee or indulge in a deliciously aromatic cup after a meal, these capsules are the perfect choice. Enjoy the convenience of a gourmet coffee experience in the comfort of your own home with these Nespresso-compatible Hazelnut flavor capsules." +
							"Experience a touch of sweetness and smoothness with the Nespresso-compatible Hazelnut flavor x 10 capsules. Every sip will transport you to a world of pleasure and satisfaction, as if you were in a luxury café. Don't miss the opportunity to elevate your coffee experience and enjoy the irresistible aroma and taste of hazelnut with these convenient and delicious capsules. Treat yourself to the delicious combination of hazelnut and coffee with just a simple push of a button.",
					6.57,
					84,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://www.lafazenda.com.ar/uploads/productos/Avellana_461.webp",
					List.of());
			productRepository.save(product63);

			Product product64 = new Product("ITALIAN RISTRETTO WHOLE BEANS",
					"Dive into the depths of bold flavor with our Italian Ristretto whole beans. The dark-roasted, Italian-style blend delivers a powerful and intense taste that is sure to awaken your senses. Indulge in the smooth and velvety texture of the deep mahogany crema, adding a touch of luxury to every sip." +
							"Crafted from 100% roasted coffee, our Italian Ristretto beans are compatible with all types of coffee machines. With a strength rating of 10/10, this coffee will satisfy even the most discerning palates, making it the perfect choice for coffee enthusiasts seeking an unparalleled coffee experience. Experience the pinnacle of intensity and flavor with our Italian Ristretto whole beans.",
					9.86,
					142,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					12.00,
					"https://www.lafazenda.com.ar/uploads/productos/Prueba_ristretto_(1).webp",
					List.of("https://www.lafazenda.com.ar/uploads/productos/prueba_ristretto_atras.webp","https://www.lafazenda.com.ar/uploads/productos/ristretto_y_cafe_con_leche_2.webp"));
			productRepository.save(product64);

			Product product65 = new Product("COLOMBIAN COFFEE BEANS",
					"Experience the rich and vibrant flavors of Colombian coffee beans. Sourced from its renowned origins, this coffee delivers an intense and invigorating cup. With a powerful and lingering taste, every sip leaves a lasting impression on your palate. The Colombian beans boast an intense character, complemented by hints of tropical fruit, a medium body, and balanced acidity." +
							"Made from 100% roasted coffee, this Colombian blend is compatible with all types of coffee machines. With a strength rating of 9/10, this coffee is perfect for those seeking a bold and robust coffee experience. Indulge in the captivating aromas and flavors that this Colombian coffee has to offer—each cup is a journey to the heart of Colombian coffee culture.",
					9.86,
					98,
					CategoryProduct.COFFEE,
					ColorProduct.NONE,
					0,
					"https://www.lafazenda.com.ar/uploads/productos/Colombia_adelante6.webp",
					List.of("https://www.lafazenda.com.ar/uploads/productos/Colombia_atras.webp"));
			productRepository.save(product65);
		};
	}
}
