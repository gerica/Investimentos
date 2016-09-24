package br.cs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cs.entity.Empresa;
import br.cs.entity.Papel;
import br.cs.execao.InvestimentoBusinessException;
import br.cs.repository.EmpresaRepository;

@Service
public class EmpresaService
{
  private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);
  @Autowired
  private EmpresaRepository empresaRepository;
  @Autowired
  private PapelService papelService;
  
  public void salvar(Empresa empresa)
    throws InvestimentoBusinessException
  {
    logger.debug("salvar");
    validarCarteira(empresa);
    this.empresaRepository.save(empresa);
  }
  
  private void validarCarteira(Empresa carteira)
    throws InvestimentoBusinessException
  {
    logger.debug("validar carteira");
    List<Empresa> list = this.empresaRepository.findByNome(carteira.getNome());
    if (!list.isEmpty())
    {
      if (list.size() == 1)
      {
        Empresa empresaBanco = (Empresa)list.iterator().next();
        if (empresaBanco.getId().equals(carteira.getId())) {
          return;
        }
      }
      throw new InvestimentoBusinessException("Nome de Empresa j� cadastrado");
    }
  }
  
  public List<Empresa> findAll()
  {
    return this.empresaRepository.findAll();
  }
  
  public List<Empresa> findAllByAtivo()
  {
    return this.empresaRepository.findAllByAtivo(Boolean.valueOf(true));
  }
  
  public Empresa findById(Integer idEmpresa)
  {
    return this.empresaRepository.findById(idEmpresa);
  }
  
  public void inativar(Integer idCarteira)
    throws InvestimentoBusinessException
  {
    Empresa empresa = findById(idCarteira);
    if (!empresa.getPapeis().isEmpty()) {
      for (Papel p : empresa.getPapeis()) {
        if (p.getAtivo().booleanValue()) {
          throw new InvestimentoBusinessException(
            "Essa empresa j� possui papel cadastrado, apage os papeis primeiro para poder excluir a emprsa");
        }
      }
    }
    empresa.setAtivo(Boolean.valueOf(false));
    salvar(empresa);
  }
}
