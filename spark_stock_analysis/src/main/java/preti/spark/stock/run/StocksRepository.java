package preti.spark.stock.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

import preti.stock.InputDataEntry;
import preti.stock.coremodel.Stock;
import preti.stock.coremodel.StockHistory;

public class StocksRepository {
	private JavaSparkContext sc;
	private Map<String, Long> stockIdsMap;

	public StocksRepository(JavaSparkContext sc) {
		super();
		this.sc = sc;

		stockIdsMap = new HashMap<>();
		stockIdsMap.put("ABCB4", 322l);
		stockIdsMap.put("ABEVF4", 705l);
		stockIdsMap.put("ABEVN4", 694l);
		stockIdsMap.put("ABEVR4", 708l);
		stockIdsMap.put("ABEVX4", 771l);
		stockIdsMap.put("ABREG4", 709l);
		stockIdsMap.put("ABRES4", 718l);
		stockIdsMap.put("ACES4", 1l);
		stockIdsMap.put("AEDUI4", 580l);
		stockIdsMap.put("AESL4", 218l);
		stockIdsMap.put("ALLL4", 2l);
		stockIdsMap.put("ALLLB4", 520l);
		stockIdsMap.put("ALLLN4", 749l);
		stockIdsMap.put("ALPA4", 3l);
		stockIdsMap.put("ALPAG4", 719l);
		stockIdsMap.put("AMBV4", 4l);
		stockIdsMap.put("AMBVA4", 334l);
		stockIdsMap.put("AMBVD4", 393l);
		stockIdsMap.put("AMBVE4", 639l);
		stockIdsMap.put("AMBVJ4", 460l);
		stockIdsMap.put("AMBVN4", 507l);
		stockIdsMap.put("AMBVS4", 355l);
		stockIdsMap.put("AMBVT4", 363l);
		stockIdsMap.put("AMBVV4", 373l);
		stockIdsMap.put("APTI4", 248l);
		stockIdsMap.put("ARLA4", 167l);
		stockIdsMap.put("ARTE4", 5l);
		stockIdsMap.put("ASSM4", 291l);
		stockIdsMap.put("ASTA4", 6l);
		stockIdsMap.put("AZEV4", 195l);
		stockIdsMap.put("BAHI4", 296l);
		stockIdsMap.put("BALM4", 125l);
		stockIdsMap.put("BAUH4", 126l);
		stockIdsMap.put("BBASA4", 465l);
		stockIdsMap.put("BBASB4", 470l);
		stockIdsMap.put("BBASC4", 448l);
		stockIdsMap.put("BBASD4", 472l);
		stockIdsMap.put("BBASE4", 551l);
		stockIdsMap.put("BBASF4", 650l);
		stockIdsMap.put("BBASH4", 500l);
		stockIdsMap.put("BBASJ4", 371l);
		stockIdsMap.put("BBASL4", 462l);
		stockIdsMap.put("BBASN4", 468l);
		stockIdsMap.put("BBASP4", 474l);
		stockIdsMap.put("BBASR4", 623l);
		stockIdsMap.put("BBAST4", 455l);
		stockIdsMap.put("BBDC4", 7l);
		stockIdsMap.put("BBDCA4", 523l);
		stockIdsMap.put("BBDCC4", 751l);
		stockIdsMap.put("BBDCD4", 388l);
		stockIdsMap.put("BBDCE4", 766l);
		stockIdsMap.put("BBDCF4", 406l);
		stockIdsMap.put("BBDCG4", 310l);
		stockIdsMap.put("BBDCH4", 315l);
		stockIdsMap.put("BBDCI4", 721l);
		stockIdsMap.put("BBDCJ4", 666l);
		stockIdsMap.put("BBDCK4", 327l);
		stockIdsMap.put("BBDCL4", 323l);
		stockIdsMap.put("BBDCO4", 744l);
		stockIdsMap.put("BBDCP4", 394l);
		stockIdsMap.put("BBDCQ4", 767l);
		stockIdsMap.put("BBDCR4", 655l);
		stockIdsMap.put("BBDCS4", 496l);
		stockIdsMap.put("BBDCT4", 366l);
		stockIdsMap.put("BBDCU4", 717l);
		stockIdsMap.put("BBDCV4", 369l);
		stockIdsMap.put("BBDCW4", 740l);
		stockIdsMap.put("BBDCX4", 611l);
		stockIdsMap.put("BBRKC4", 768l);
		stockIdsMap.put("BBSEE4", 777l);
		stockIdsMap.put("BBSEI4", 772l);
		stockIdsMap.put("BBSEL4", 756l);
		stockIdsMap.put("BBSEP4", 776l);
		stockIdsMap.put("BBTGF4", 710l);
		stockIdsMap.put("BBTGH4", 716l);
		stockIdsMap.put("BBTGX4", 736l);
		stockIdsMap.put("BDLL4", 8l);
		stockIdsMap.put("BECE4", 205l);
		stockIdsMap.put("BEES4", 354l);
		stockIdsMap.put("BELG4", 9l);
		stockIdsMap.put("BESP4", 127l);
		stockIdsMap.put("BFIT4", 172l);
		stockIdsMap.put("BGIP4", 128l);
		stockIdsMap.put("BICB4", 326l);
		stockIdsMap.put("BIOM4", 10l);
		stockIdsMap.put("BISAD4", 625l);
		stockIdsMap.put("BISAI4", 586l);
		stockIdsMap.put("BISAK4", 568l);
		stockIdsMap.put("BMBR4", 303l);
		stockIdsMap.put("BMEB4", 162l);
		stockIdsMap.put("BMIN4", 196l);
		stockIdsMap.put("BMTO4", 129l);
		stockIdsMap.put("BNBR4", 11l);
		stockIdsMap.put("BOBR4", 12l);
		stockIdsMap.put("BOVAR4", 730l);
		stockIdsMap.put("BOVAS4", 452l);
		stockIdsMap.put("BPAN4", 725l);
		stockIdsMap.put("BPNM4", 329l);
		stockIdsMap.put("BRAP4", 13l);
		stockIdsMap.put("BRAPA4", 531l);
		stockIdsMap.put("BRAPB4", 621l);
		stockIdsMap.put("BRAPF4", 351l);
		stockIdsMap.put("BRAPH4", 572l);
		stockIdsMap.put("BRAPI4", 583l);
		stockIdsMap.put("BRFSC4", 754l);
		stockIdsMap.put("BRFSG4", 699l);
		stockIdsMap.put("BRFSL4", 738l);
		stockIdsMap.put("BRFSN4", 619l);
		stockIdsMap.put("BRFSO4", 758l);
		stockIdsMap.put("BRFSV4", 674l);
		stockIdsMap.put("BRIV4", 14l);
		stockIdsMap.put("BRKMJ4", 262l);
		stockIdsMap.put("BRKMK4", 267l);
		stockIdsMap.put("BRKML4", 271l);
		stockIdsMap.put("BRKMP4", 477l);
		stockIdsMap.put("BRKMV4", 503l);
		stockIdsMap.put("BRSR4", 163l);
		stockIdsMap.put("BRTO4", 15l);
		stockIdsMap.put("BRTOJ4", 264l);
		stockIdsMap.put("BRTOK4", 266l);
		stockIdsMap.put("BRTOL4", 269l);
		stockIdsMap.put("BRTP4", 16l);
		stockIdsMap.put("BSLI4", 381l);
		stockIdsMap.put("BTOWU4", 729l);
		stockIdsMap.put("BTTL4", 320l);
		stockIdsMap.put("BUET4", 17l);
		stockIdsMap.put("BVMFA4", 535l);
		stockIdsMap.put("BVMFB4", 536l);
		stockIdsMap.put("BVMFC4", 386l);
		stockIdsMap.put("BVMFE4", 395l);
		stockIdsMap.put("BVMFK4", 521l);
		stockIdsMap.put("BVMFL4", 537l);
		stockIdsMap.put("BVMFP4", 630l);
		stockIdsMap.put("BVMFR4", 403l);
		stockIdsMap.put("BVMFS4", 382l);
		stockIdsMap.put("BVMFW4", 524l);
		stockIdsMap.put("CAFE4", 18l);
		stockIdsMap.put("CALI4", 222l);
		stockIdsMap.put("CAMB4", 232l);
		stockIdsMap.put("CASN4", 285l);
		stockIdsMap.put("CBMA4", 192l);
		stockIdsMap.put("CCHI4", 224l);
		stockIdsMap.put("CCROE4", 555l);
		stockIdsMap.put("CCROX4", 701l);
		stockIdsMap.put("CCTU4", 180l);
		stockIdsMap.put("CCXCJ4", 581l);
		stockIdsMap.put("CEDO4", 201l);
		stockIdsMap.put("CEED4", 459l);
		stockIdsMap.put("CESP4", 19l);
		stockIdsMap.put("CESPG4", 412l);
		stockIdsMap.put("CESPP4", 340l);
		stockIdsMap.put("CGRA4", 20l);
		stockIdsMap.put("CHAP4", 21l);
		stockIdsMap.put("CIELC4", 763l);
		stockIdsMap.put("CIELG4", 573l);
		stockIdsMap.put("CIELL4", 514l);
		stockIdsMap.put("CIELR4", 764l);
		stockIdsMap.put("CIQU4", 22l);
		stockIdsMap.put("CLAN4", 387l);
		stockIdsMap.put("CLSC4", 543l);
		stockIdsMap.put("CLSCI4", 256l);
		stockIdsMap.put("CMET4", 23l);
		stockIdsMap.put("CMETB4", 130l);
		stockIdsMap.put("CMETD4", 183l);
		stockIdsMap.put("CMETE4", 217l);
		stockIdsMap.put("CMETG4", 240l);
		stockIdsMap.put("CMETI4", 249l);
		stockIdsMap.put("CMGR4", 156l);
		stockIdsMap.put("CMIG4", 24l);
		stockIdsMap.put("CMIGD4", 527l);
		stockIdsMap.put("CMIGF4", 220l);
		stockIdsMap.put("CMIGG4", 242l);
		stockIdsMap.put("CMIGK4", 324l);
		stockIdsMap.put("CMIGL4", 272l);
		stockIdsMap.put("CMIGR4", 221l);
		stockIdsMap.put("CMMA4", 131l);
		stockIdsMap.put("CNFB4", 25l);
		stockIdsMap.put("CNFBK4", 261l);
		stockIdsMap.put("CORR4", 132l);
		stockIdsMap.put("CPFEX4", 539l);
		stockIdsMap.put("CPFG4", 157l);
		stockIdsMap.put("CPFP4", 164l);
		stockIdsMap.put("CPLEH4", 720l);
		stockIdsMap.put("CPLEO4", 193l);
		stockIdsMap.put("CRIV4", 26l);
		stockIdsMap.put("CSAB4", 187l);
		stockIdsMap.put("CSANA4", 746l);
		stockIdsMap.put("CSAND4", 678l);
		stockIdsMap.put("CSANF4", 493l);
		stockIdsMap.put("CSANL4", 509l);
		stockIdsMap.put("CSANT4", 722l);
		stockIdsMap.put("CSANU4", 734l);
		stockIdsMap.put("CSMGB4", 548l);
		stockIdsMap.put("CSNAA4", 377l);
		stockIdsMap.put("CSNAB4", 440l);
		stockIdsMap.put("CSNAC4", 446l);
		stockIdsMap.put("CSNAD4", 391l);
		stockIdsMap.put("CSNAE4", 401l);
		stockIdsMap.put("CSNAF4", 349l);
		stockIdsMap.put("CSNAH4", 364l);
		stockIdsMap.put("CSNAI4", 254l);
		stockIdsMap.put("CSNAJ4", 263l);
		stockIdsMap.put("CSNAK4", 441l);
		stockIdsMap.put("CSNAL4", 274l);
		stockIdsMap.put("CSNAM4", 319l);
		stockIdsMap.put("CSNAN4", 471l);
		stockIdsMap.put("CSNAO4", 473l);
		stockIdsMap.put("CSNAP4", 759l);
		stockIdsMap.put("CSNAQ4", 449l);
		stockIdsMap.put("CSNAR4", 494l);
		stockIdsMap.put("CSNAS4", 413l);
		stockIdsMap.put("CSNAU4", 425l);
		stockIdsMap.put("CSNAV4", 434l);
		stockIdsMap.put("CSNAX4", 442l);
		stockIdsMap.put("CSPC4", 27l);
		stockIdsMap.put("CSTB4", 28l);
		stockIdsMap.put("CSTBI4", 246l);
		stockIdsMap.put("CTAX4", 258l);
		stockIdsMap.put("CTIPB4", 593l);
		stockIdsMap.put("CTIPC4", 550l);
		stockIdsMap.put("CTIPE4", 757l);
		stockIdsMap.put("CTIPF4", 644l);
		stockIdsMap.put("CTIPH4", 587l);
		stockIdsMap.put("CTIPI4", 724l);
		stockIdsMap.put("CTIPJ4", 510l);
		stockIdsMap.put("CTIPN4", 508l);
		stockIdsMap.put("CTIPO4", 597l);
		stockIdsMap.put("CTIPR4", 651l);
		stockIdsMap.put("CTIPS4", 783l);
		stockIdsMap.put("CTKA4", 185l);
		stockIdsMap.put("CTNM4", 29l);
		stockIdsMap.put("CTPC4", 306l);
		stockIdsMap.put("CTSA4", 197l);
		stockIdsMap.put("CYRE4", 30l);
		stockIdsMap.put("CYREA4", 616l);
		stockIdsMap.put("CYREP4", 775l);
		stockIdsMap.put("CYREX4", 522l);
		stockIdsMap.put("CZRS4", 312l);
		stockIdsMap.put("DASAG4", 343l);
		stockIdsMap.put("DAYC4", 313l);
		stockIdsMap.put("DFVA4", 278l);
		stockIdsMap.put("DHBI4", 158l);
		stockIdsMap.put("DIRRB4", 695l);
		stockIdsMap.put("DJON4", 168l);
		stockIdsMap.put("DOCA4", 165l);
		stockIdsMap.put("DOHL4", 212l);
		stockIdsMap.put("DPPI4", 133l);
		stockIdsMap.put("DUQE4", 206l);
		stockIdsMap.put("DURA4", 31l);
		stockIdsMap.put("DXTG4", 32l);
		stockIdsMap.put("EALT4", 146l);
		stockIdsMap.put("EBCO4", 33l);
		stockIdsMap.put("EBEN4", 34l);
		stockIdsMap.put("EBTP4", 35l);
		stockIdsMap.put("EBTPA4", 134l);
		stockIdsMap.put("EBTPB4", 178l);
		stockIdsMap.put("EBTPC4", 179l);
		stockIdsMap.put("EBTPD4", 198l);
		stockIdsMap.put("EBTPE4", 213l);
		stockIdsMap.put("EBTPF4", 233l);
		stockIdsMap.put("EBTPG4", 243l);
		stockIdsMap.put("EBTPH4", 244l);
		stockIdsMap.put("EBTPI4", 252l);
		stockIdsMap.put("EBTPJ4", 255l);
		stockIdsMap.put("EBTPL4", 268l);
		stockIdsMap.put("ECPR4", 210l);
		stockIdsMap.put("EEEL4", 458l);
		stockIdsMap.put("EKTR4", 184l);
		stockIdsMap.put("ELCA4", 173l);
		stockIdsMap.put("ELEK4", 36l);
		stockIdsMap.put("ELETA4", 299l);
		stockIdsMap.put("ELETD4", 335l);
		stockIdsMap.put("ELETE4", 283l);
		stockIdsMap.put("ELETF4", 646l);
		stockIdsMap.put("ELETG4", 647l);
		stockIdsMap.put("ELETH4", 360l);
		stockIdsMap.put("ELETI4", 253l);
		stockIdsMap.put("ELETK4", 735l);
		stockIdsMap.put("ELETL4", 273l);
		stockIdsMap.put("ELETP4", 336l);
		stockIdsMap.put("ELETX4", 318l);
		stockIdsMap.put("ELPL4", 37l);
		stockIdsMap.put("ELPLA4", 615l);
		stockIdsMap.put("ELUM4", 38l);
		stockIdsMap.put("EMAE4", 39l);
		stockIdsMap.put("EMBR4", 40l);
		stockIdsMap.put("ENGI4", 307l);
		stockIdsMap.put("ENMT4", 787l);
		stockIdsMap.put("EQTLG4", 773l);
		stockIdsMap.put("EQTLP4", 750l);
		stockIdsMap.put("ESTR4", 41l);
		stockIdsMap.put("ETER4", 42l);
		stockIdsMap.put("EUCA4", 207l);
		stockIdsMap.put("EZTCB4", 557l);
		stockIdsMap.put("EZTCD4", 561l);
		stockIdsMap.put("FBMC4", 135l);
		stockIdsMap.put("FBRA4", 136l);
		stockIdsMap.put("FCAP4", 137l);
		stockIdsMap.put("FESA4", 43l);
		stockIdsMap.put("FFTL4", 44l);
		stockIdsMap.put("FGUI4", 138l);
		stockIdsMap.put("FIGE4", 228l);
		stockIdsMap.put("FJTA4", 45l);
		stockIdsMap.put("FLRYB4", 595l);
		stockIdsMap.put("FLRYD4", 545l);
		stockIdsMap.put("FRAS4", 46l);
		stockIdsMap.put("FTRX4", 204l);
		stockIdsMap.put("GAZO4", 47l);
		stockIdsMap.put("GEPA4", 48l);
		stockIdsMap.put("GETI4", 49l);
		stockIdsMap.put("GETIW4", 532l);
		stockIdsMap.put("GFSAA4", 687l);
		stockIdsMap.put("GFSAB4", 690l);
		stockIdsMap.put("GFSAC4", 631l);
		stockIdsMap.put("GFSAD4", 556l);
		stockIdsMap.put("GFSAE4", 563l);
		stockIdsMap.put("GFSAH4", 649l);
		stockIdsMap.put("GFSAJ4", 670l);
		stockIdsMap.put("GFSAK4", 604l);
		stockIdsMap.put("GFSAO4", 541l);
		stockIdsMap.put("GFSAQ4", 641l);
		stockIdsMap.put("GFSAW4", 605l);
		stockIdsMap.put("GGBR4", 50l);
		stockIdsMap.put("GGBRA4", 378l);
		stockIdsMap.put("GGBRB4", 761l);
		stockIdsMap.put("GGBRC4", 188l);
		stockIdsMap.put("GGBRD4", 383l);
		stockIdsMap.put("GGBRE4", 398l);
		stockIdsMap.put("GGBRF4", 234l);
		stockIdsMap.put("GGBRG4", 713l);
		stockIdsMap.put("GGBRH4", 415l);
		stockIdsMap.put("GGBRI4", 257l);
		stockIdsMap.put("GGBRJ4", 428l);
		stockIdsMap.put("GGBRL4", 270l);
		stockIdsMap.put("GGBRO4", 380l);
		stockIdsMap.put("GGBRP4", 384l);
		stockIdsMap.put("GGBRR4", 402l);
		stockIdsMap.put("GGBRS4", 409l);
		stockIdsMap.put("GGBRT4", 416l);
		stockIdsMap.put("GGBRU4", 727l);
		stockIdsMap.put("GGBRV4", 429l);
		stockIdsMap.put("GGBRW4", 372l);
		stockIdsMap.put("GLOB4", 147l);
		stockIdsMap.put("GOAU4", 51l);
		stockIdsMap.put("GOLL4", 52l);
		stockIdsMap.put("GOLLB4", 755l);
		stockIdsMap.put("GRNL4", 53l);
		stockIdsMap.put("GUAR4", 54l);
		stockIdsMap.put("HAGA4", 186l);
		stockIdsMap.put("HETA4", 251l);
		stockIdsMap.put("HGTX4", 55l);
		stockIdsMap.put("HGTXG4", 571l);
		stockIdsMap.put("HGTXI4", 723l);
		stockIdsMap.put("HGTXK4", 676l);
		stockIdsMap.put("HGTXU4", 726l);
		stockIdsMap.put("HGTXW4", 673l);
		stockIdsMap.put("HGTXX4", 582l);
		stockIdsMap.put("HOOT4", 148l);
		stockIdsMap.put("HRTPC4", 511l);
		stockIdsMap.put("HRTPD4", 634l);
		stockIdsMap.put("HRTPE4", 637l);
		stockIdsMap.put("HRTPL4", 497l);
		stockIdsMap.put("HYPEA4", 525l);
		stockIdsMap.put("HYPEH4", 492l);
		stockIdsMap.put("IBOVB4", 691l);
		stockIdsMap.put("IBOVH4", 559l);
		stockIdsMap.put("IBOVJ4", 484l);
		stockIdsMap.put("IBOVL4", 447l);
		stockIdsMap.put("IBOVN4", 731l);
		stockIdsMap.put("IBOVT4", 552l);
		stockIdsMap.put("IBOVX4", 445l);
		stockIdsMap.put("IDVL4", 317l);
		stockIdsMap.put("ILMD4", 56l);
		stockIdsMap.put("IMBI4", 57l);
		stockIdsMap.put("INEP4", 58l);
		stockIdsMap.put("ITAU4", 59l);
		stockIdsMap.put("ITAUD4", 149l);
		stockIdsMap.put("ITAUS4", 347l);
		stockIdsMap.put("ITSA4", 60l);
		stockIdsMap.put("ITSAA4", 325l);
		stockIdsMap.put("ITSAB4", 339l);
		stockIdsMap.put("ITSAC4", 342l);
		stockIdsMap.put("ITSAD4", 636l);
		stockIdsMap.put("ITSAE4", 700l);
		stockIdsMap.put("ITSAF4", 483l);
		stockIdsMap.put("ITSAG4", 411l);
		stockIdsMap.put("ITSAI4", 420l);
		stockIdsMap.put("ITSAJ4", 321l);
		stockIdsMap.put("ITSAK4", 594l);
		stockIdsMap.put("ITSAL4", 534l);
		stockIdsMap.put("ITSAM4", 512l);
		stockIdsMap.put("ITSAQ4", 785l);
		stockIdsMap.put("ITSAR4", 485l);
		stockIdsMap.put("ITSAS4", 417l);
		stockIdsMap.put("ITSAU4", 778l);
		stockIdsMap.put("ITSAW4", 732l);
		stockIdsMap.put("ITSAX4", 502l);
		stockIdsMap.put("ITUB4", 407l);
		stockIdsMap.put("ITUBA4", 741l);
		stockIdsMap.put("ITUBB4", 505l);
		stockIdsMap.put("ITUBC4", 760l);
		stockIdsMap.put("ITUBD4", 478l);
		stockIdsMap.put("ITUBE4", 562l);
		stockIdsMap.put("ITUBF4", 642l);
		stockIdsMap.put("ITUBG4", 714l);
		stockIdsMap.put("ITUBH4", 479l);
		stockIdsMap.put("ITUBI4", 577l);
		stockIdsMap.put("ITUBJ4", 480l);
		stockIdsMap.put("ITUBK4", 438l);
		stockIdsMap.put("ITUBL4", 660l);
		stockIdsMap.put("ITUBM4", 742l);
		stockIdsMap.put("ITUBN4", 682l);
		stockIdsMap.put("ITUBO4", 762l);
		stockIdsMap.put("ITUBP4", 696l);
		stockIdsMap.put("ITUBQ4", 560l);
		stockIdsMap.put("ITUBR4", 643l);
		stockIdsMap.put("ITUBS4", 728l);
		stockIdsMap.put("ITUBT4", 648l);
		stockIdsMap.put("ITUBU4", 578l);
		stockIdsMap.put("ITUBV4", 481l);
		stockIdsMap.put("ITUBW4", 528l);
		stockIdsMap.put("ITUBX4", 498l);
		stockIdsMap.put("JBDU4", 61l);
		stockIdsMap.put("JOPA4", 276l);
		stockIdsMap.put("KLBN4", 62l);
		stockIdsMap.put("KLBNI4", 290l);
		stockIdsMap.put("KROT4", 553l);
		stockIdsMap.put("KROTF4", 711l);
		stockIdsMap.put("KROTR4", 712l);
		stockIdsMap.put("LAME4", 63l);
		stockIdsMap.put("LARK4", 311l);
		stockIdsMap.put("LCSA4", 292l);
		stockIdsMap.put("LECO4", 202l);
		stockIdsMap.put("LEVE4", 64l);
		stockIdsMap.put("LFFE4", 199l);
		stockIdsMap.put("LHER4", 245l);
		stockIdsMap.put("LIGTL4", 540l);
		stockIdsMap.put("LIXC4", 65l);
		stockIdsMap.put("LLXLD4", 547l);
		stockIdsMap.put("LREN4", 150l);
		stockIdsMap.put("LRENT4", 786l);
		stockIdsMap.put("LUXM4", 159l);
		stockIdsMap.put("MAPT4", 169l);
		stockIdsMap.put("MDIAU4", 569l);
		stockIdsMap.put("MERC4", 181l);
		stockIdsMap.put("MGEL4", 66l);
		stockIdsMap.put("MLFT4", 139l);
		stockIdsMap.put("MLPA4", 216l);
		stockIdsMap.put("MMXMK4", 439l);
		stockIdsMap.put("MMXMM4", 397l);
		stockIdsMap.put("MMXMW4", 606l);
		stockIdsMap.put("MNDL4", 67l);
		stockIdsMap.put("MNPR4", 68l);
		stockIdsMap.put("MNSA4", 69l);
		stockIdsMap.put("MPLUL4", 737l);
		stockIdsMap.put("MRFGF4", 486l);
		stockIdsMap.put("MRFGG4", 488l);
		stockIdsMap.put("MRFGH4", 489l);
		stockIdsMap.put("MRFGR4", 487l);
		stockIdsMap.put("MRFGS4", 490l);
		stockIdsMap.put("MRFGT4", 491l);
		stockIdsMap.put("MRSL4", 170l);
		stockIdsMap.put("MSPA4", 208l);
		stockIdsMap.put("MTBR4", 70l);
		stockIdsMap.put("MTIG4", 239l);
		stockIdsMap.put("MTSA4", 71l);
		stockIdsMap.put("MULTD4", 769l);
		stockIdsMap.put("MWET4", 72l);
		stockIdsMap.put("MYPK4", 73l);
		stockIdsMap.put("MYPKP4", 526l);
		stockIdsMap.put("NAFG4", 352l);
		stockIdsMap.put("NATUI4", 592l);
		stockIdsMap.put("NATUJ4", 585l);
		stockIdsMap.put("NATUV4", 599l);
		stockIdsMap.put("NEMO4", 226l);
		stockIdsMap.put("NETC4", 229l);
		stockIdsMap.put("NETCA4", 297l);
		stockIdsMap.put("NETCB4", 300l);
		stockIdsMap.put("NETCC4", 301l);
		stockIdsMap.put("NETCD4", 389l);
		stockIdsMap.put("NETCE4", 230l);
		stockIdsMap.put("NETCF4", 238l);
		stockIdsMap.put("NETCG4", 241l);
		stockIdsMap.put("NETCH4", 247l);
		stockIdsMap.put("NETCI4", 250l);
		stockIdsMap.put("NETCJ4", 426l);
		stockIdsMap.put("NETCK4", 293l);
		stockIdsMap.put("NETCL4", 295l);
		stockIdsMap.put("NETCM4", 298l);
		stockIdsMap.put("NETCN4", 375l);
		stockIdsMap.put("NETCO4", 302l);
		stockIdsMap.put("NETCQ4", 345l);
		stockIdsMap.put("NETCR4", 348l);
		stockIdsMap.put("NETCS4", 353l);
		stockIdsMap.put("NETCT4", 357l);
		stockIdsMap.put("NETCU4", 365l);
		stockIdsMap.put("NETCV4", 427l);
		stockIdsMap.put("NETCW4", 294l);
		stockIdsMap.put("NETCX4", 436l);
		stockIdsMap.put("ODPVL4", 607l);
		stockIdsMap.put("OGXPA4", 504l);
		stockIdsMap.put("OGXPB4", 609l);
		stockIdsMap.put("OGXPC4", 613l);
		stockIdsMap.put("OGXPD4", 626l);
		stockIdsMap.put("OGXPE4", 629l);
		stockIdsMap.put("OGXPF4", 410l);
		stockIdsMap.put("OGXPG4", 574l);
		stockIdsMap.put("OGXPH4", 423l);
		stockIdsMap.put("OGXPI4", 424l);
		stockIdsMap.put("OGXPJ4", 435l);
		stockIdsMap.put("OGXPK4", 430l);
		stockIdsMap.put("OGXPL4", 433l);
		stockIdsMap.put("OGXPM4", 530l);
		stockIdsMap.put("OGXPN4", 614l);
		stockIdsMap.put("OGXPO4", 620l);
		stockIdsMap.put("OGXPP4", 627l);
		stockIdsMap.put("OGXPQ4", 635l);
		stockIdsMap.put("OGXPR4", 624l);
		stockIdsMap.put("OGXPS4", 576l);
		stockIdsMap.put("OGXPT4", 575l);
		stockIdsMap.put("OGXPU4", 590l);
		stockIdsMap.put("OGXPV4", 601l);
		stockIdsMap.put("OGXPW4", 603l);
		stockIdsMap.put("OGXPX4", 432l);
		stockIdsMap.put("OIBR4", 558l);
		stockIdsMap.put("OIBRA4", 680l);
		stockIdsMap.put("OIBRB4", 686l);
		stockIdsMap.put("OIBRC4", 688l);
		stockIdsMap.put("OIBRD4", 770l);
		stockIdsMap.put("OIBRE4", 645l);
		stockIdsMap.put("OIBRF4", 652l);
		stockIdsMap.put("OIBRG4", 656l);
		stockIdsMap.put("OIBRH4", 657l);
		stockIdsMap.put("OIBRI4", 661l);
		stockIdsMap.put("OIBRJ4", 667l);
		stockIdsMap.put("OIBRK4", 672l);
		stockIdsMap.put("OIBRL4", 669l);
		stockIdsMap.put("OIBRM4", 683l);
		stockIdsMap.put("OIBRN4", 693l);
		stockIdsMap.put("OIBRO4", 697l);
		stockIdsMap.put("OIBRU4", 668l);
		stockIdsMap.put("OIBRV4", 671l);
		stockIdsMap.put("OIBRW4", 679l);
		stockIdsMap.put("OIBRX4", 681l);
		stockIdsMap.put("OSAO4", 74l);
		stockIdsMap.put("PATI4", 75l);
		stockIdsMap.put("PCAR4", 76l);
		stockIdsMap.put("PCARA4", 684l);
		stockIdsMap.put("PCARC4", 692l);
		stockIdsMap.put("PCARD4", 698l);
		stockIdsMap.put("PCARE4", 702l);
		stockIdsMap.put("PCARH4", 706l);
		stockIdsMap.put("PCARR4", 703l);
		stockIdsMap.put("PCARU4", 663l);
		stockIdsMap.put("PCARX4", 739l);
		stockIdsMap.put("PDGRA4", 622l);
		stockIdsMap.put("PDGRB4", 612l);
		stockIdsMap.put("PDGRC4", 628l);
		stockIdsMap.put("PDGRD4", 518l);
		stockIdsMap.put("PDGRF4", 564l);
		stockIdsMap.put("PDGRG4", 565l);
		stockIdsMap.put("PDGRH4", 579l);
		stockIdsMap.put("PDGRI4", 588l);
		stockIdsMap.put("PDGRJ4", 589l);
		stockIdsMap.put("PDGRK4", 570l);
		stockIdsMap.put("PDGRL4", 608l);
		stockIdsMap.put("PDGRN4", 617l);
		stockIdsMap.put("PDGRQ4", 451l);
		stockIdsMap.put("PDGRR4", 566l);
		stockIdsMap.put("PDGRS4", 567l);
		stockIdsMap.put("PDGRU4", 596l);
		stockIdsMap.put("PDGRV4", 598l);
		stockIdsMap.put("PDGRW4", 450l);
		stockIdsMap.put("PEAB4", 77l);
		stockIdsMap.put("PETR4", 78l);
		stockIdsMap.put("PETRA4", 338l);
		stockIdsMap.put("PETRB4", 466l);
		stockIdsMap.put("PETRC4", 337l);
		stockIdsMap.put("PETRD4", 341l);
		stockIdsMap.put("PETRE4", 280l);
		stockIdsMap.put("PETRF4", 284l);
		stockIdsMap.put("PETRG4", 475l);
		stockIdsMap.put("PETRH4", 287l);
		stockIdsMap.put("PETRI4", 259l);
		stockIdsMap.put("PETRJ4", 260l);
		stockIdsMap.put("PETRK4", 515l);
		stockIdsMap.put("PETRL4", 330l);
		stockIdsMap.put("PETRM4", 463l);
		stockIdsMap.put("PETRN4", 467l);
		stockIdsMap.put("PETRO4", 469l);
		stockIdsMap.put("PETRP4", 546l);
		stockIdsMap.put("PETRQ4", 281l);
		stockIdsMap.put("PETRR4", 408l);
		stockIdsMap.put("PETRS4", 476l);
		stockIdsMap.put("PETRT4", 288l);
		stockIdsMap.put("PETRU4", 454l);
		stockIdsMap.put("PETRV4", 370l);
		stockIdsMap.put("PETRW4", 461l);
		stockIdsMap.put("PETRX4", 374l);
		stockIdsMap.put("PINE4", 304l);
		stockIdsMap.put("PITI4", 219l);
		stockIdsMap.put("PLAS4", 279l);
		stockIdsMap.put("PLDN4", 160l);
		stockIdsMap.put("PLIM4", 79l);
		stockIdsMap.put("PLIME4", 227l);
		stockIdsMap.put("PMAM4", 80l);
		stockIdsMap.put("PMAMC4", 203l);
		stockIdsMap.put("PMAMF4", 542l);
		stockIdsMap.put("PNVL4", 209l);
		stockIdsMap.put("POMO4", 81l);
		stockIdsMap.put("POMOF4", 715l);
		stockIdsMap.put("POPR4", 194l);
		stockIdsMap.put("PORP4", 174l);
		stockIdsMap.put("PQUN4", 171l);
		stockIdsMap.put("PRBC4", 314l);
		stockIdsMap.put("PRGA4", 82l);
		stockIdsMap.put("PTBL4", 151l);
		stockIdsMap.put("PTIP4", 83l);
		stockIdsMap.put("PTNT4", 140l);
		stockIdsMap.put("PTPA4", 166l);
		stockIdsMap.put("PTQS4", 84l);
		stockIdsMap.put("RADLE4", 780l);
		stockIdsMap.put("RANI4", 152l);
		stockIdsMap.put("RAPT4", 85l);
		stockIdsMap.put("RAPTQ4", 748l);
		stockIdsMap.put("RCSL4", 86l);
		stockIdsMap.put("RDCDC4", 544l);
		stockIdsMap.put("REDE4", 289l);
		stockIdsMap.put("REEM4", 161l);
		stockIdsMap.put("RENTE4", 704l);
		stockIdsMap.put("RENTJ4", 675l);
		stockIdsMap.put("RHDS4", 316l);
		stockIdsMap.put("RIPI4", 87l);
		stockIdsMap.put("RNPT4", 153l);
		stockIdsMap.put("ROMI4", 141l);
		stockIdsMap.put("ROMID4", 214l);
		stockIdsMap.put("RPMG4", 176l);
		stockIdsMap.put("RPSA4", 88l);
		stockIdsMap.put("RSIDA4", 618l);
		stockIdsMap.put("RSIDE4", 640l);
		stockIdsMap.put("RSIDF4", 659l);
		stockIdsMap.put("RSIDH4", 584l);
		stockIdsMap.put("RSIDJ4", 600l);
		stockIdsMap.put("RSIDK4", 602l);
		stockIdsMap.put("RSIDL4", 610l);
		stockIdsMap.put("RSIDQ4", 399l);
		stockIdsMap.put("RSIDS4", 654l);
		stockIdsMap.put("RSIP4", 142l);
		stockIdsMap.put("RSUL4", 182l);
		stockIdsMap.put("SALM4", 89l);
		stockIdsMap.put("SANB4", 305l);
		stockIdsMap.put("SANBV4", 457l);
		stockIdsMap.put("SAPR4", 90l);
		stockIdsMap.put("SCAR4", 91l);
		stockIdsMap.put("SCLO4", 92l);
		stockIdsMap.put("SDIA4", 93l);
		stockIdsMap.put("SDIAC4", 200l);
		stockIdsMap.put("SFSA4", 309l);
		stockIdsMap.put("SGAS4", 143l);
		stockIdsMap.put("SGEN4", 94l);
		stockIdsMap.put("SHUL4", 95l);
		stockIdsMap.put("SJOS4", 154l);
		stockIdsMap.put("SLED4", 96l);
		stockIdsMap.put("SMLER4", 779l);
		stockIdsMap.put("SMLEX4", 743l);
		stockIdsMap.put("STRP4", 97l);
		stockIdsMap.put("SULT4", 144l);
		stockIdsMap.put("SUZBF4", 782l);
		stockIdsMap.put("SUZBI4", 591l);
		stockIdsMap.put("SUZBP4", 784l);
		stockIdsMap.put("SUZBR4", 781l);
		stockIdsMap.put("SZPQ4", 98l);
		stockIdsMap.put("TAMM4", 231l);
		stockIdsMap.put("TANC4", 99l);
		stockIdsMap.put("TBLEA4", 774l);
		stockIdsMap.put("TCNO4", 100l);
		stockIdsMap.put("TCOC4", 101l);
		stockIdsMap.put("TCSL4", 102l);
		stockIdsMap.put("TDBH4", 103l);
		stockIdsMap.put("TEKA4", 104l);
		stockIdsMap.put("TELB4", 105l);
		stockIdsMap.put("TKNO4", 106l);
		stockIdsMap.put("TLCP4", 107l);
		stockIdsMap.put("TLCPB4", 189l);
		stockIdsMap.put("TLPP4", 108l);
		stockIdsMap.put("TLPPA4", 501l);
		stockIdsMap.put("TMCP4", 109l);
		stockIdsMap.put("TNCP4", 110l);
		stockIdsMap.put("TNCPB4", 190l);
		stockIdsMap.put("TNCPC4", 191l);
		stockIdsMap.put("TNLP4", 111l);
		stockIdsMap.put("TNLPG4", 235l);
		stockIdsMap.put("TNLPS4", 236l);
		stockIdsMap.put("TOTSU4", 664l);
		stockIdsMap.put("TOYB4", 112l);
		stockIdsMap.put("TRFO4", 113l);
		stockIdsMap.put("TRNA4", 308l);
		stockIdsMap.put("TROR4", 225l);
		stockIdsMap.put("TRPL4", 114l);
		stockIdsMap.put("TRPLE4", 517l);
		stockIdsMap.put("TRPLH4", 362l);
		stockIdsMap.put("TRPLJ4", 519l);
		stockIdsMap.put("TSEP4", 115l);
		stockIdsMap.put("TSPP4", 116l);
		stockIdsMap.put("TUPY4", 175l);
		stockIdsMap.put("TXRX4", 117l);
		stockIdsMap.put("UBBR4", 118l);
		stockIdsMap.put("UBBRA4", 333l);
		stockIdsMap.put("UBBRD4", 344l);
		stockIdsMap.put("UCOP4", 155l);
		stockIdsMap.put("UGPA4", 119l);
		stockIdsMap.put("UGPAA4", 464l);
		stockIdsMap.put("UGPAC4", 689l);
		stockIdsMap.put("UGPAG4", 358l);
		stockIdsMap.put("UGPAK4", 367l);
		stockIdsMap.put("UOLL4", 277l);
		stockIdsMap.put("USIMA4", 331l);
		stockIdsMap.put("USIMB4", 443l);
		stockIdsMap.put("USIMC4", 385l);
		stockIdsMap.put("USIMD4", 390l);
		stockIdsMap.put("USIME4", 400l);
		stockIdsMap.put("USIMF4", 350l);
		stockIdsMap.put("USIMG4", 356l);
		stockIdsMap.put("USIMH4", 359l);
		stockIdsMap.put("USIMI4", 421l);
		stockIdsMap.put("USIMK4", 265l);
		stockIdsMap.put("USIML4", 275l);
		stockIdsMap.put("USIMM4", 747l);
		stockIdsMap.put("USIMN4", 685l);
		stockIdsMap.put("USIMO4", 444l);
		stockIdsMap.put("USIMP4", 392l);
		stockIdsMap.put("USIMQ4", 482l);
		stockIdsMap.put("USIMR4", 404l);
		stockIdsMap.put("USIMS4", 414l);
		stockIdsMap.put("USIMT4", 418l);
		stockIdsMap.put("USIMV4", 422l);
		stockIdsMap.put("USIMW4", 431l);
		stockIdsMap.put("USIMX4", 752l);
		stockIdsMap.put("VAGRF4", 653l);
		stockIdsMap.put("VAGRQ4", 638l);
		stockIdsMap.put("VAGV4", 120l);
		stockIdsMap.put("VALEA4", 533l);
		stockIdsMap.put("VALEB4", 376l);
		stockIdsMap.put("VALEC4", 632l);
		stockIdsMap.put("VALED4", 549l);
		stockIdsMap.put("VALEE4", 215l);
		stockIdsMap.put("VALEF4", 405l);
		stockIdsMap.put("VALEG4", 237l);
		stockIdsMap.put("VALEH4", 506l);
		stockIdsMap.put("VALEI4", 419l);
		stockIdsMap.put("VALEJ4", 499l);
		stockIdsMap.put("VALEK4", 437l);
		stockIdsMap.put("VALEL4", 328l);
		stockIdsMap.put("VALEM4", 332l);
		stockIdsMap.put("VALEN4", 379l);
		stockIdsMap.put("VALEO4", 513l);
		stockIdsMap.put("VALEP4", 554l);
		stockIdsMap.put("VALEQ4", 633l);
		stockIdsMap.put("VALER4", 453l);
		stockIdsMap.put("VALES4", 223l);
		stockIdsMap.put("VALET4", 456l);
		stockIdsMap.put("VALEU4", 361l);
		stockIdsMap.put("VALEV4", 346l);
		stockIdsMap.put("VALEW4", 368l);
		stockIdsMap.put("VALEX4", 495l);
		stockIdsMap.put("VCPA4", 121l);
		stockIdsMap.put("VCPAD4", 396l);
		stockIdsMap.put("VGOR4", 145l);
		stockIdsMap.put("VIVO4", 282l);
		stockIdsMap.put("VIVT4", 516l);
		stockIdsMap.put("VIVTA4", 529l);
		stockIdsMap.put("VIVTC4", 753l);
		stockIdsMap.put("VIVTL4", 538l);
		stockIdsMap.put("VIVTM4", 745l);
		stockIdsMap.put("VLIDG4", 658l);
		stockIdsMap.put("VLIDH4", 662l);
		stockIdsMap.put("VLIDI4", 665l);
		stockIdsMap.put("VLIDJ4", 733l);
		stockIdsMap.put("VLIDL4", 677l);
		stockIdsMap.put("VLIDX4", 707l);
		stockIdsMap.put("VPSC4", 122l);
		stockIdsMap.put("VPTA4", 177l);
		stockIdsMap.put("VULC4", 211l);
		stockIdsMap.put("WEGE4", 123l);
		stockIdsMap.put("WEGEP4", 765l);
		stockIdsMap.put("WHRL4", 286l);
		stockIdsMap.put("WISA4", 124l);

	}

	public List<Stock> loadStocks(String stockHistoryFile, List<String> stockCodesToAnalyze) {
		JavaRDD<InputDataEntry> inputData = sc.textFile(stockHistoryFile).filter(s -> !s.trim().isEmpty())
				.map(InputDataEntry::parseFromLine);
		inputData.persist(StorageLevel.MEMORY_ONLY());

		List<Stock> stocks = new ArrayList<>();

		for (String stockCode : stockCodesToAnalyze) {
			List<InputDataEntry> stockEntries = inputData.filter(sd -> sd.getCode().equals(stockCode)).collect();
			if (stockEntries.isEmpty())
				continue;

			Stock stock = new Stock(stockIdsMap.get(stockCode), stockCode);
			stocks.add(stock);
			for (InputDataEntry data : stockEntries) {
				stock.addHistory(new StockHistory(0, stock.getId(), data.getDate(), data.getHigh(), data.getLow(), data.getClose(),
						data.getVolume()));
			}
		}
		return stocks;
	}

}
