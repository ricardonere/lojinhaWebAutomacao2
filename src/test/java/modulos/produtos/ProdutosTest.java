package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web no Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;
    @BeforeEach
    public void beforeEach(){
        //Abrir um navegador
        this.navegador = new ChromeDriver();
        //Vou maximizar a tela
        this.navegador.manage().window().maximize();
        //Vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Vou navegar para a a pagina da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }
@Test
@DisplayName("Nao é permitido registrar produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){
    //Vou fazer login("Usando PAGE OBJECT E FLUENT PAGE para CONVERTER MUITAS LINHAS DE CÓDIGO EM POUCAS LINHAS")
    String mensagemApresentada = new LoginPage(navegador)
            .informarOUsuario("admin")
                    .informarASenha("admin")
                            .submeterFormularioDeLogin()
                                    .acessarFormularioAdicaoNovoProduto()
                                            .informarNomeDoProduto("Macbook Pro")
                                                    .informarValorDoProduto("000")
                                                            .informarCoresDoProduto("preto,branco")
            .submeterFormularioDeAdicaoComErro()
                    .capturarMensagemApresentada();

    //vou validar que a mensagem de erro foi apresentada

    Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

}
@Test
@DisplayName("Nao e permitido registrar produto com valor acima de R$7.000,00 ")
public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMilReais(){
        //Vou fazer login("Usando PAGE OBJECT E FLUENT PAGE para CONVERTER MUITAS LINHAS DE CÓDIGO EM POUCAS LINHAS")
    String mensagemApresentada = new LoginPage(navegador)
            .informarOUsuario("admin")
            .informarASenha("admin")
            .submeterFormularioDeLogin()
            .acessarFormularioAdicaoNovoProduto()
            .informarNomeDoProduto("Macbook Pro")
            .informarValorDoProduto("700001")
            .informarCoresDoProduto("preto,branco")
            .submeterFormularioDeAdicaoComErro()
            .capturarMensagemApresentada();

    //vou validar que a mensagem de erro foi apresentada

    Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

}
@Test
@DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7.000,00 reais ")
    public void testPossoAdicionarProdutosComValorDeUmCentavoASeteMilReais(){
String mensagemApresentada = new LoginPage(navegador)
        .informarOUsuario("admin")
        .informarASenha("admin")
        .submeterFormularioDeLogin()
        .acessarFormularioAdicaoNovoProduto()
        .informarNomeDoProduto("IPHONE13")
        .informarValorDoProduto("69990")
        .informarCoresDoProduto("azul, vermelho")
        .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
//Produto adicionado com sucesso
}

@AfterEach
    public void afterEach(){
    //Vou fechar o navegador
    navegador.quit();
}
}
