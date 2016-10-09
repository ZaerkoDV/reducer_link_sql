package com.instinctools.reducerlink.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.instinctools.reducerlink.model.Link;
import com.instinctools.reducerlink.model.User;
import com.instinctools.reducerlink.service.LinkService;
import com.instinctools.reducerlink.service.support.ObjUtils;

@Controller
public class LinkController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "link/byId/get", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByIdGet(@RequestBody Map<String, Object> request) {
        Link link = linkService.getLinkById(
            ObjUtils.asLong(request, "id")
        );

        return buildOk(toMap(
            "id", link.getId(),
            "tag", link.getTag(),
            "comment", link.getComment(),
            "shotUrl", link.getShortUrl(),
            "fullUrl", link.getFullUrl()
        ));
    }

    @RequestMapping(value = "link/new/create", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkNewCreate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            linkService.createLink((new Link())
                .setTag(ObjUtils.asString(request, "tag"))
                .setComment(ObjUtils.asString(request, "comment"))
                .setFullUrl(ObjUtils.asString(request, "fullUrl"))
                .setUser(new User()
                .setId(ObjUtils.asLong(ObjUtils.asObject(request, "user"), "id"))),
                getCurrentTimestamp()
             ), link -> toMap(
                 "idLink", link.getId(),
                 "shortUrl", link.getShortUrl()
             ));
    }

    @RequestMapping(value = "link/byId/update", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByIdUpdate(@RequestBody Map<String, Object> request) {
        return buildValidationResult(
            linkService.updateLink((new Link())
                .setId(ObjUtils.asLong(request, "id"))
                .setTag(ObjUtils.asString(request, "tag"))
                .setComment(ObjUtils.asString(request, "comment"))
                .setFullUrl(ObjUtils.asString(request, "fullUrl")),
                getCurrentTimestamp()
            ), link -> toMap(
                "idLink", link.getId()
            ));
    }

    @RequestMapping(value = "link/byId/delete", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByIdDelete(@RequestBody Map<String, Object> request) {
        return buildOk(linkService.deleteLink(
            ObjUtils.asLong(request, "id")
        ));
    }

    @RequestMapping(value = "link/byIdUser/getList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByIdUserGetList(@RequestBody Map<String, Object> request) {
        return buildPagedResult(linkService.getListLinkByIdUser(
            ObjUtils.asLong(request, "id"),
            ObjUtils.asString(request, "orderBy", "tag"),
            ObjUtils.asBoolean(request, "orderAsc"),
            ObjUtils.asInteger(request, "pageNum"),
            Math.min(MAX_PAGE_SIZE, ObjUtils.asInteger(request, "pageSize"))
         ), link -> toMap(
             "id", link.getId(),
             "tag", link.getTag(),
             "comment", link.getComment(),
             "shortUrl", link.getFullUrl(),
             "fullUrl", link.getFullUrl()
         ));
     }

    @RequestMapping(value = "link/byTag/getList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByTagGetList(@RequestBody Map<String, Object> request) {
        List<Link> listLink = linkService.getListLinkByTag(
            ObjUtils.asString(request, "tag"),
            ObjUtils.asString(request, "orderBy", "id"),
            ObjUtils.asBoolean(request, "orderAsc")
        );

        return buildOk(
            mapList(listLink, link -> toMap(
                "id", link.getId(),
                "tag", link.getTag(),
                "comment", link.getComment(),
                "shortUrl", link.getShortUrl(),
                "idUser", link.getUser().getId()
            ))
        );
    }

    @RequestMapping(value = "link/ByDate/getList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkByDateGetList(@RequestBody Map<String, Object> request) {
        List<Link> listLink = linkService.getListLinkBetweenDate(
            ObjUtils.asLong(request, "startTimestamp"),
            ObjUtils.asLong(request, "endTimestamp")
        );

        return buildOk(
            mapList(listLink, link -> toMap(
                "id", link.getId(),
                "tag", link.getTag(),
                "comment", link.getComment(),
                "shortUrl", link.getShortUrl()
            ))
        );
    }

    @RequestMapping(value = "link/uniqual/getTagList", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkUniqualGrtTagList(@RequestBody Map<String, Object> request) {
        return buildOk(linkService.getListUniqueTag());
    }

    @RequestMapping(value = "link/numberLinkVisits/increase", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostLinkNumberLinkVisitsIncrease(@RequestBody Map<String, Object> request) {
        Long incresedSumClick = linkService.increaseNumberLinkVisits(
            ObjUtils.asLong(request, "id")
        );

        return buildOk(incresedSumClick);
    }
}