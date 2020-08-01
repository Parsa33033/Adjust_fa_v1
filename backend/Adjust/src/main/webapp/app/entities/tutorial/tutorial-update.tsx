import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITutorialVideo } from 'app/shared/model/tutorial-video.model';
import { getEntities as getTutorialVideos } from 'app/entities/tutorial-video/tutorial-video.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { getEntities as getAdjustClients } from 'app/entities/adjust-client/adjust-client.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './tutorial.reducer';
import { ITutorial } from 'app/shared/model/tutorial.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITutorialUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TutorialUpdate = (props: ITutorialUpdateProps) => {
  const [videoId, setVideoId] = useState('0');
  const [clientId, setClientId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tutorialEntity, tutorialVideos, adjustClients, loading, updating } = props;

  const { text, thumbnail, thumbnailContentType } = tutorialEntity;

  const handleClose = () => {
    props.history.push('/tutorial');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTutorialVideos();
    props.getAdjustClients();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...tutorialEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.tutorial.home.createOrEditLabel">
            <Translate contentKey="adjustApp.tutorial.home.createOrEditLabel">Create or edit a Tutorial</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tutorialEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tutorial-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tutorial-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="titleLabel" for="tutorial-title">
                  <Translate contentKey="adjustApp.tutorial.title">Title</Translate>
                </Label>
                <AvField id="tutorial-title" type="text" name="title" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="tutorial-description">
                  <Translate contentKey="adjustApp.tutorial.description">Description</Translate>
                </Label>
                <AvField id="tutorial-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="textLabel" for="tutorial-text">
                  <Translate contentKey="adjustApp.tutorial.text">Text</Translate>
                </Label>
                <AvInput id="tutorial-text" type="textarea" name="text" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="thumbnailLabel" for="thumbnail">
                    <Translate contentKey="adjustApp.tutorial.thumbnail">Thumbnail</Translate>
                  </Label>
                  <br />
                  {thumbnail ? (
                    <div>
                      {thumbnailContentType ? (
                        <a onClick={openFile(thumbnailContentType, thumbnail)}>
                          <img src={`data:${thumbnailContentType};base64,${thumbnail}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {thumbnailContentType}, {byteSize(thumbnail)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('thumbnail')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_thumbnail" type="file" onChange={onBlobChange(true, 'thumbnail')} accept="image/*" />
                  <AvInput type="hidden" name="thumbnail" value={thumbnail} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label id="tokenPriceLabel" for="tutorial-tokenPrice">
                  <Translate contentKey="adjustApp.tutorial.tokenPrice">Token Price</Translate>
                </Label>
                <AvField id="tutorial-tokenPrice" type="string" className="form-control" name="tokenPrice" />
              </AvGroup>
              <AvGroup>
                <Label id="adjustTutorialIdLabel" for="tutorial-adjustTutorialId">
                  <Translate contentKey="adjustApp.tutorial.adjustTutorialId">Adjust Tutorial Id</Translate>
                </Label>
                <AvField id="tutorial-adjustTutorialId" type="string" className="form-control" name="adjustTutorialId" />
              </AvGroup>
              <AvGroup>
                <Label for="tutorial-video">
                  <Translate contentKey="adjustApp.tutorial.video">Video</Translate>
                </Label>
                <AvInput id="tutorial-video" type="select" className="form-control" name="videoId">
                  <option value="" key="0" />
                  {tutorialVideos
                    ? tutorialVideos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="tutorial-client">
                  <Translate contentKey="adjustApp.tutorial.client">Client</Translate>
                </Label>
                <AvInput id="tutorial-client" type="select" className="form-control" name="clientId">
                  <option value="" key="0" />
                  {adjustClients
                    ? adjustClients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tutorial" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tutorialVideos: storeState.tutorialVideo.entities,
  adjustClients: storeState.adjustClient.entities,
  tutorialEntity: storeState.tutorial.entity,
  loading: storeState.tutorial.loading,
  updating: storeState.tutorial.updating,
  updateSuccess: storeState.tutorial.updateSuccess,
});

const mapDispatchToProps = {
  getTutorialVideos,
  getAdjustClients,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TutorialUpdate);
